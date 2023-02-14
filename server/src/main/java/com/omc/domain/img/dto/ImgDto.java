package com.omc.domain.img.dto;

import lombok.Builder;
import lombok.Getter;

public class ImgDto {

	@Getter
	@Builder
	public static class Request {
		private String imgName;
		private String imgUrl;
	}
}
