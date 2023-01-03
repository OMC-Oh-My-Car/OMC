package com.omc.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageInfo {
	private int page;
	private int size = 20;
	private long totalElements;
	private int totalPages;
}
