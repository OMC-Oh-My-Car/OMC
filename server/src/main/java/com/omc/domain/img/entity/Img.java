package com.omc.domain.img.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.omc.domain.product.entity.Product;
import com.omc.global.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Img extends BaseEntity {

	@Column(nullable = false)
	private String imgUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Product product;



	public Img(String imgUrl, Product product) {
		this.imgUrl = imgUrl;
		this.product = product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
