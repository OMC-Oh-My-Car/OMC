package com.omc.domain.img.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.omc.domain.product.entity.Product;
import com.omc.global.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ProductImg extends BaseEntity {

	@Column(nullable = false)
	private String imgUrl;

	@Column(nullable = false)
	private String imgName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Product product;

	@Builder
	public ProductImg(String imgUrl, Product product, String imgName) {
		this.imgUrl = imgUrl;
		this.product = product;
		this.imgName = imgName;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
