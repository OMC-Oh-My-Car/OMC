package com.omc.domain.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.img.entity.Img;
import com.omc.domain.product.entity.Product;
import com.omc.domain.img.repository.ImgRepository;
import com.omc.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

	// private final MemberRepository memberRepository;
	// private final S3Service s3Service;
	private final ProductRepository productRepository;
	private final ImgRepository imgRepository;

	/**
	 * 상품 등록
	 * @param productRequest: 상품 정보
	 *                     - subject: 상품명
	 *                     - description: 상품 설명
	 * @param imgPaths: 상품 이미지 경로
	 */
	@Transactional
	public void uploadProduct(ProductDto.Request productRequest, List<String> imgPaths) {

		Product product = Product.builder()
			.subject(productRequest.getSubject())
			.description(productRequest.getDescription())
			.build();

		productRepository.save(product);

		List<String> imgList = new ArrayList<>();

		for (String imgUrl : imgPaths) {
			Img img = new Img(imgUrl, product);
			imgRepository.save(img);
			imgList.add(img.getImgUrl());
		}
	}

	/*
	todo : Product Delete 구현시 S3 이미지 삭제 적용

	List<Img> imgList = imgRepository.findAllByProduct(product);
	for (Img img : imgList) {
		s3UploadService.deleteFile(img.getImgName());
	}
	 */
}
