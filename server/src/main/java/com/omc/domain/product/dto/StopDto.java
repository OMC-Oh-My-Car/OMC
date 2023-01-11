package com.omc.domain.product.dto;

import java.util.List;

import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StopDto {

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Request {

		@Pattern(regexp = "^[0-2]$", message = "판매 상태값은 0, 1, 2만 가능합니다.")
		private Long isStop; // 0: 판매중, 1: 판매중지, 2: 블라인드

		@Pattern(regexp = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\s]{0,100}$", message = "판매중지 사유는 100자 이내로 입력해주세요.")
		private String stopReason;

	}

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	public static class Response {
		private Long productId;
		private String subject;
		private String description;
		private List<String> locations;
		private Long reportCount;
		private Long price;
		private Double star;
		private List<String> img;
		private Long likes;
		private Long isStop;
		private String stopReason;

	}
}
