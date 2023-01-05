package com.omc.domain.product.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;

import com.omc.domain.img.entity.Img;
import com.omc.domain.member.entity.Member;
import com.omc.global.common.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseEntity {

	@Column(nullable = false)
	private String subject;

	@Column
	private String description;

	@Column
	private String address;

	@Column
	private String zipcode;

	@Column
	private String location;

	@Column
	private Long reportCount;

	@Column
	private String telephone;

	@Column
	private Long count;

	@Column
	private Long price;

	@Column
	private Double star;

	@Column
	private String checkIn;

	@Column
	private String checkOut;

	@Column
	private Long likes;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Img> imgList = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Facilities> facilities = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Stop> stops = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	private Member member;

	@Builder
	public Product(String subject, String description, List<Img> imgList, String address, String zipcode,
		String location, Long reportCount, String telephone, Long count, Long price, Double star, String checkIn,
		String checkOut, Long likes, List<Facilities> facilities) {
		this.subject = subject;
		this.description = description;
		this.imgList = imgList;
		for (Img img : imgList) {
			img.setProduct(this);
		}
		this.address = address;
		this.zipcode = zipcode;
		this.location = location;
		this.reportCount = reportCount;
		this.telephone = telephone;
		this.count = count;
		this.price = price;
		this.star = star;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.likes = likes;
	}

}
