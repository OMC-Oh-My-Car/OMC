package com.omc.global.util;

import java.io.InputStream;
import java.util.ArrayList;
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
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.omc.domain.img.dto.ImgDto;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

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
	 *
	 * @param inputStream    파일 스트림
	 * @param objectMetadata 파일 메타데이터
	 * @param fileName       파일 이름
	 */
	public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
		amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata).withCannedAcl(
			CannedAccessControlList.PublicRead));
	}

	/**
	 * File URL 가져오기
	 *
	 * @param fileName 파일 이름
	 * @return 파일 URL
	 */
	public String getFileUrl(String fileName) {
		return amazonS3.getUrl(bucket, fileName).toString();
	}

	/**
	 * S3에 파일 삭제
	 *
	 * @param fileName 파일 이름
	 */
	public void deleteFile(String fileName) {
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, fileName);
		amazonS3.deleteObject(deleteObjectRequest);
	}

	/**
	 * 이미지 업로드
	 *
	 * @param file    이미지 파일
	 * @param dirName 디렉토리 이름
	 * @return 이미지 URL
	 */
	@SneakyThrows
	public ImgDto.Request uploadImage(MultipartFile file, String dirName) {

		String filename = dirName + "/" + createFileName(file.getOriginalFilename());

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(file.getSize());
		objectMetadata.setContentType(file.getContentType());
		InputStream inputStream = file.getInputStream();

		uploadFile(inputStream, objectMetadata, filename);

		return ImgDto.Request.builder()
							 .imgName(filename)
							 .imgUrl(getFileUrl(filename))
							 .build();
	}

	/**
	 * 중복 파일명 방지를 위한 UUID 파일명 생성
	 *
	 * @param fileName 파일 이름
	 * @return UUID 파일 이름
	 */
	private String createFileName(String fileName) {
		return UUID.randomUUID().toString().concat(getFileExtension(fileName));
	}

	/**
	 * 이미지 확장자 검증
	 *
	 * @param fileName 파일 이름
	 * @return 이미지 확장자
	 */
	private String getFileExtension(String fileName) {

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
