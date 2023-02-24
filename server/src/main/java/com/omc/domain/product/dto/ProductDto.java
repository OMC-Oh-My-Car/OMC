package com.omc.domain.product.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Request {

		@NotBlank(message = "상품 이름을 입력해주세요.")
		@Size(min = 1, max = 30, message = "상품 이름은 1자 이상 30자 이하로 입력해주세요.")
		private String subject;

		@NotBlank(message = "상품 설명을 입력해주세요.")
		@Size(min = 1, max = 100, message = "상품 설명은 1자 이상 100자 이하로 입력해주세요.")
		private String description;

		@NotBlank(message = "상품 주소를 입력해주세요.")
		@Size(min = 1, max = 100, message = "상품 주소는 1자 이상 100자 이하로 입력해주세요.")
		private String address;

		@NotBlank(message = "상품 우편번호를 입력해주세요.")
		@Length(min = 5, max = 5, message = "상품 우편번호는 5자리로 입력해주세요.")
		private String zipcode;

		@NotBlank(message = "상품 해시태그를 입력해주세요.")
		@Pattern(regexp = "^(#[a-zA-Z0-9가-힣]{1,10}(\\s{1,10})?)+$", message = "상품 해시태그는 #으로 시작하고 1자 이상 10자 이하로 입력해주세요.")
		private String facilities;

		@NotBlank(message = "상품 전화번호를 입력해주세요.")
		@Length(min = 10, max = 11, message = "상품 전화번호는 10자리 또는 11자리로 입력해주세요.")
		private String telephone;

		@NotNull(message = "상품 가격을 입력해주세요.")
		@Positive(message = "상품 가격은 음수일 수 없습니다.")
		private Long price;

		@NotNull(message = "상품 체크인 시간을를 입력해주세요.")
		@Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "상품 체크인 시간은 HH:mm 형식으로 입력해주세요.")
		private String checkIn;

		@NotNull(message = "상품 체크아웃 시간을를 입력해주세요.")
		@Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "상품 체크아웃 시간은 HH:mm 형식으로 입력해주세요.")
		private String checkOut;
	}

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	public static class Response {
		private Long id;
		private String subject;
		private String description;
		private String address;
		private String zipcode;
		private List<String> locations;
		private List<String> facilities;
		private Long reportCount;
		private String telephone;
		private Long price;
		private Double star;
		private String checkIn;
		private String checkOut;
		private List<String> img;
		private Long likes;
		private Boolean isLike;
		private Long isStop;

	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Search {
		private Long page;
		private Long size;
		private String sort;
		private String facilities;
		private String query;

		public Search(@RequestParam(value = "page") Long page,
					  @RequestParam(value = "sort", required = false) String sort,
					  @RequestParam(value = "tag", required = false) String facilities,
					  @RequestParam(value = "query", required = false) String query) {
			this.page = page == null ? 1 : page;
			this.sort = sort == null ? "id" : sort;
			this.size = 20L;
			this.facilities = facilities;
			this.query = query;
		}

	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class ResponseForAdmin {
		private Long productId;
		private Long isStop;
		private String productImage;
		private Long reportCount;
		private String seller;
		private String stopReason;

		@Builder
		public ResponseForAdmin(Long productId, Long isStop, String productImage, Long reportCount, String seller,
								String stopReason) {
			this.productId = productId;
			this.isStop = isStop;
			this.productImage = productImage;
			this.reportCount = reportCount;
			this.seller = seller;
			this.stopReason = stopReason;
		}
	}
}
