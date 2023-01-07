package com.omc.domain.product.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.omc.domain.img.entity.Img;
import com.omc.domain.member.entity.Member;
import com.omc.domain.product.dto.ProductDto;
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

	@ColumnDefault("0")
	@Column
	private Long isStop;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Img> imgList = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Facilities> facilities = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Location> locations = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StopHistory> stopHistories = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	private Member member;

	@OneToMany(mappedBy = "product")
	private List<LikeHistory> likeHistories = new ArrayList<>();

	@Builder
	public Product(String subject, String description, List<Img> imgList, String address, String zipcode,
				   Long reportCount, String telephone, Long count, Long price, Double star, String checkIn,
				   String checkOut, Long likes, Long isStop) {
		this.subject = subject;
		this.description = description;
		this.imgList = imgList;
		for (Img img : imgList) {
			img.setProduct(this);
		}
		this.address = address;
		this.zipcode = zipcode;
		this.reportCount = reportCount;
		this.telephone = telephone;
		this.count = count;
		this.price = price;
		this.star = star;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.likes = likes;
		this.isStop = isStop;
	}

	public void editProduct(ProductDto.Request dto) {
		Optional.ofNullable(dto.getSubject())
				.ifPresent(subject -> this.subject = subject);
		Optional.ofNullable(dto.getDescription())
				.ifPresent(description -> this.description = description);
		Optional.ofNullable(dto.getAddress())
				.ifPresent(address -> this.address = address);
		Optional.ofNullable(dto.getZipcode())
				.ifPresent(zipcode -> this.zipcode = zipcode);
		Optional.ofNullable(dto.getTelephone())
				.ifPresent(telephone -> this.telephone = telephone);
		Optional.ofNullable(dto.getPrice())
				.ifPresent(price -> this.price = price);
		Optional.ofNullable(dto.getCheckIn())
				.ifPresent(checkIn -> this.checkIn = checkIn);
		Optional.ofNullable(dto.getCheckOut())
				.ifPresent(checkOut -> this.checkOut = checkOut);
	}

	public void setImgList(List<Img> imgs) {
		this.imgList = imgs;
		for (Img img : imgs) {
			img.setProduct(this);
		}
	}

	public void like() {
		this.likes += 1;
	}

	public void disLike() {
		this.likes -= 1;
	}

	public void setIsStop(Long isStop) {
		this.isStop = isStop;
	}

	public void addStopHistory(List<StopHistory> stopHistories) {
		this.stopHistories = stopHistories;
		for (StopHistory stopHistory : stopHistories) {
			stopHistory.setProduct(this);
		}
	}
}
