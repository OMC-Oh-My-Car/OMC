package com.omc.domain.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.omc.domain.img.dto.ImgDto;
import com.omc.domain.img.entity.Img;
import com.omc.domain.img.repository.ImgRepository;
import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.product.entity.Product;
import com.omc.domain.product.repository.ProductRepository;
import com.omc.global.util.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

	// private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	private final ImgRepository imgRepository;

	private final S3Service s3Service;

	/**
	 * 상품 등록
	 * @param request: 상품 정보
	 *                     - subject: 상품명
	 *                     - description: 상품 설명
	 * @param multipartFiles: 상품 이미지 경로
	 */
	@SneakyThrows
	@Transactional
	public void uploadProduct(ProductDto.Request request, List<MultipartFile> multipartFiles) {

		log.info("multipartFile imgs={}", multipartFiles);

		List<Img> imgList = new ArrayList<>();
		List<ImgDto.Request> imgDtoList = new ArrayList<>();

		if (multipartFiles != null) {
			for (MultipartFile img : multipartFiles) {
				ImgDto.Request imgDto = s3Service.uploadImage(img, "product");
				imgDtoList.add(imgDto);
			}
		}

		imgDtoToImg(imgList, imgDtoList);

		Product product = Product.builder()
			.subject(request.getSubject())
			.description(request.getDescription())
			.imgList(imgList)
			.build();

		productRepository.save(product);

	}

	/**
	 * ImgDto를 Img로 변환
	 * @param imgList : Img 리스트
	 * @param imgDtoList : ImgDto 리스트
	 */
	private static void imgDtoToImg(List<Img> imgList, List<ImgDto.Request> imgDtoList) {
		for (ImgDto.Request imgDto : imgDtoList) {
			Img img = Img.builder()
				.imgName(imgDto.getImgName())
				.imgUrl(imgDto.getImgUrl())
				.build();
			imgList.add(img);
		}
	}
}
