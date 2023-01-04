package com.omc.domain.product.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.product.service.ProductService;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;
import com.omc.domain.img.service.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;
	private final S3Service s3Service;

	/**
	 * 상품 등록 (개발중)
	 * @param productRequest: 상품 정보
	 *                     - subject: 상품명
	 *                     - description: 상품 설명
	 * @param multipartFiles: 상품 이미지
	 */
	@PostMapping(value = "/product", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> create(@RequestPart("product") ProductDto.Request productRequest,
		@RequestPart("imgUrl") List<MultipartFile> multipartFiles) {

		if (multipartFiles == null) {
			throw new BusinessException(ErrorCode.IMAGE_NOT_FOUND);
		}

		List<String> imgPaths = s3Service.upload(multipartFiles);
		log.info("IMG 경로 : " + imgPaths);

		productService.uploadProduct(productRequest, imgPaths);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
