package com.omc.domain.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.omc.global.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Facilities extends BaseEntity {

	@Column
	private String keyword;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Product product;

	@Builder
	public Facilities(String keyword, Product product) {
		this.product = product;
		this.keyword = keyword;
	}
}
