package com.omc.domain.product.api;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.product.service.ProductService;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;

	/**
	 * 상품 등록
	 * @param req: 상품 정보
	 * @param multipartFiles: 상품 이미지
	 */
	@PostMapping(value = "/product",
		consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> create(@RequestPart("product") ProductDto.Request req,
		@RequestPart("imgUrl") List<MultipartFile> multipartFiles) {

		if (multipartFiles == null) {
			log.error("multipartFiles is null");
			throw new BusinessException(ErrorCode.IMAGE_NOT_FOUND);
		}

		productService.create(req, multipartFiles);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * 상품 수정
	 * @param req: 수정할 상품 정보
	 * @param multipartFiles: 상품 이미지
	 */
	@PatchMapping(value = "/product/{productId}",
		consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> update(@RequestPart("product") ProductDto.Request req,
		@RequestPart("imgUrl") List<MultipartFile> multipartFiles,
		@PathVariable Long productId) {

		productService.update(req, multipartFiles, productId);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * 상품 삭제
	 * @param productId : 상품 아이디
	 */
	@DeleteMapping(value = "/product/{productId}")
	public ResponseEntity<?> delete(@PathVariable Long productId) {

		productService.delete(productId);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
