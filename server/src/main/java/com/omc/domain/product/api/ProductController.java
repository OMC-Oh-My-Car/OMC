package com.omc.domain.product.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.service.MemberService;
import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.product.dto.StopDto;
import com.omc.domain.product.entity.Product;
import com.omc.domain.product.service.ProductService;
import com.omc.global.common.annotation.CurrentMember;
import com.omc.global.common.dto.MultiResponse;
import com.omc.global.common.dto.SingleResponseDto;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;
	private final MemberService memberService;

	/**
	 * 상품 등록
	 *
	 * @param contents: 상품 정보
	 */
	@PostMapping(value = "/product",
				 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> create(@RequestPart(value = "product") ProductDto.Request contents,
									HttpServletRequest request,
									@CurrentMember AuthMember member) throws IllegalStateException, IOException {

		Member findMember = memberService.findByEmail(member.getEmail())
										 .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		List<MultipartFile> multipartFiles;

		if (multipartHttpServletRequest.getFiles("imgUrl").isEmpty()) {
			log.error("multipartFiles is null");
			throw new BusinessException(ErrorCode.IMAGE_NOT_FOUND);
		} else {
			multipartFiles = multipartHttpServletRequest.getFiles("imgUrl");
		}

		productService.create(contents, multipartFiles, findMember);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * 상품 수정
	 *
	 * @param req:            수정할 상품 정보
	 */
	@PatchMapping(value = "/product/{productId}",
				  consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> update(@RequestPart("product") ProductDto.Request req,
									HttpServletRequest request,
									@PathVariable Long productId,
									@CurrentMember AuthMember member) throws IllegalStateException, IOException {

		Member findMember = memberService.findByEmail(member.getEmail())
										 .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		List<MultipartFile> multipartFiles = multipartHttpServletRequest.getFiles("imgUrl");

		productService.update(req, multipartFiles, productId, findMember);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * 상품 상세 조회
	 *
	 * @param productId : 상품 id
	 * @return 상품 정보
	 */
	@GetMapping(value = "/product/{productId}")
	public ResponseEntity<?> get(@PathVariable Long productId,
								 @CurrentMember AuthMember member) {

		Member findMember = null;
		if (member != null) {
			findMember = memberService.findByEmail(member.getEmail())
									  .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));
		}

		ProductDto.Response res = productService.getProduct(productId, findMember);

		return new ResponseEntity<>(new SingleResponseDto<>(res), HttpStatus.OK);
	}

	/**
	 * 상품 목록 조회 (편의시설, 검색어)
	 *
	 * @param search : 검색어, 편의시설
	 * @return 상품 목록
	 */
	@GetMapping(value = "/product")
	public ResponseEntity<?> getProductList(@ModelAttribute ProductDto.Search search) {

		log.info("page: {}", search.getPage());
		log.info("sort: {}", search.getSort());
		log.info("facilities: {}", search.getFacilities());
		log.info("query: {}", search.getQuery());

		Page<Product> resPage = productService.getProductList(search);
		List<ProductDto.Response> res = productService.convertToResponse(resPage.getContent());

		return new ResponseEntity<>(new MultiResponse<>(res, resPage), HttpStatus.OK);
	}

	/**
	 * 등록한 상품 조회 (내 상품)
	 *
	 * @param search : 검색 조건
	 * @return 상품 정보
	 */
	@GetMapping(value = "/product/my")
	public ResponseEntity<?> getMyProductList(@ModelAttribute ProductDto.Search search,
											  @CurrentMember AuthMember member) {

		Member findMember = memberService.findByEmail(member.getEmail())
										 .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

		Page<Product> resPage = productService.getMyProductList(findMember, search);
		List<ProductDto.Response> res = productService.convertToResponse(resPage.getContent());

		return new ResponseEntity<>(new MultiResponse<>(res, resPage), HttpStatus.OK);
	}

	/**
	 * 상품 삭제
	 *
	 * @param productId : 상품 아이디
	 */
	@DeleteMapping(value = "/product/{productId}")
	public ResponseEntity<?> delete(@PathVariable Long productId,
									@CurrentMember AuthMember member) {

		Member findMember = memberService.findByEmail(member.getEmail())
										 .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

		productService.delete(productId, findMember);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * 상품 추천
	 *
	 * @param productId : 상품 아이디
	 */
	@PostMapping(value = "/product/{productId}/like")
	public ResponseEntity<?> like(@PathVariable Long productId,
								  @CurrentMember AuthMember member) {

		Member findMember = memberService.findByEmail(member.getEmail())
										 .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

		productService.likeProduct(productId, findMember);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * 상품 상태 관리
	 *
	 * @param productId : 상품 아이디
	 * @param req       :
	 *                  - isStop : 0: 판매중, 1: 판매중지, 2: 블라인드
	 *                  - stopReason : 상품 상태 변경 사유
	 * @return 상품 정보
	 */
	@PatchMapping(value = "/product/{productId}/stop")
	public ResponseEntity<?> status(@PathVariable Long productId,
									@RequestBody StopDto.Request req,
									@CurrentMember AuthMember member) {

		Member findMember = memberService.findByEmail(member.getEmail())
										 .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

		StopDto.Response res = productService.setStatus(productId, req, findMember);

		return new ResponseEntity<>(new SingleResponseDto<>(res), HttpStatus.OK);
	}

}
