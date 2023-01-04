package com.omc.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

public class ProductDto {

	@Getter
	@Builder
	public static class Request {
		private String subject;
		private String description;
	}

	@Getter
	@Builder
	public static class Response {
		private String subject;
		private String description;
	}
}
