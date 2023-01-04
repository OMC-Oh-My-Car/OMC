package com.omc.domain.img.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class S3Service {

	private final AmazonS3 amazonS3;
	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.region.static}")
	private String region;

	@PostConstruct
	public AmazonS3Client amazonS3Client() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
		return (AmazonS3Client)AmazonS3ClientBuilder.standard()
			.withRegion(region)
			.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
			.build();
	}

	/**
	 * S3에 파일 업로드
	 * @param multipartFile: 업로드할 파일 리스트
	 * @return List<String>: 업로드된 파일의 URL
	 */
	public List<String> upload(List<MultipartFile> multipartFile) {
		List<String> imgUrlList = new ArrayList<>();

		// forEach 구문을 통해 multipartFile로 넘어온 파일들 하나씩 fileNameList에 추가
		for (MultipartFile file : multipartFile) {
			String fileName = createFileName(file.getOriginalFilename());
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(file.getSize());
			objectMetadata.setContentType(file.getContentType());

			try (InputStream inputStream = file.getInputStream()) {
				amazonS3.putObject(
					new PutObjectRequest(bucket + "/product/image", fileName, inputStream, objectMetadata)
						.withCannedAcl(CannedAccessControlList.PublicRead));
				imgUrlList.add(amazonS3.getUrl(bucket + "/product/image", fileName).toString());
			} catch (IOException e) {
				throw new BusinessException(ErrorCode.IMAGE_UPLOAD_ERROR);
			}
		}
		return imgUrlList;
	}

	/*
	todo 추후 삭제 기능과 같이 구현
	public void deleteFile(String fileName){
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket + "/product/image", fileName);
		amazonS3.deleteObject(deleteObjectRequest);
	}
	 */

	/**
	 * 파일 이름 중복 방지를 위해 UUID를 이용해 파일 이름 생성
	 * @param fileName: 파일 이름
	 * @return String: UUID를 이용해 생성된 파일 이름
	 */
	private String createFileName(String fileName) {
		return UUID.randomUUID().toString().concat(getFileExtension(fileName));
	}

	/**
	 * 파일 유효성 검사
	 * @param fileName: 파일 이름
	 * @return String: 파일 확장자
	 */
	private String getFileExtension(String fileName) {

		if (fileName.length() == 0) {
			throw new BusinessException(ErrorCode.WRONG_INPUT_IMAGE);
		}

		// todo: 상수로 빼서 관리
		ArrayList<String> fileValidate = new ArrayList<>();
		fileValidate.add(".jpg");
		fileValidate.add(".jpeg");
		fileValidate.add(".png");
		fileValidate.add(".JPG");
		fileValidate.add(".JPEG");
		fileValidate.add(".PNG");

		String idxFileName = fileName.substring(fileName.lastIndexOf("."));
		if (!fileValidate.contains(idxFileName)) {
			throw new BusinessException(ErrorCode.WRONG_IMAGE_FORMAT);
		}

		return fileName.substring(fileName.lastIndexOf("."));
	}
}
