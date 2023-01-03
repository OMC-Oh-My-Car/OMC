package com.omc.global.common.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class MultiResponse<T> {

	private List<T> data;
	private PageInfo pageInfo;

	public MultiResponse(List<T> data, Page page) {
		this.data = data;
		this.pageInfo = new PageInfo(page.getNumber() + 1,
			page.getSize(), page.getTotalElements(), page.getTotalPages());
	}
}
