package com.omc.domain.product.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.omc.domain.img.entity.Img;
import com.omc.global.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

	@Column(nullable = false)
	private String subject;

	@Column
	private String description;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Img> imgList = new ArrayList<>();

	@Builder
	public Product(String subject, String description, List<Img> imgList) {
		this.subject = subject;
		this.description = description;
		this.imgList = imgList;
		for (Img img : imgList) {
			img.setProduct(this);
		}
	}
}
