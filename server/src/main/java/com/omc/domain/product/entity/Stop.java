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

@Getter
@NoArgsConstructor
@Entity
public class Stop extends BaseEntity {

	@Column
	private Long isStop;

	@Column
	private String reason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Product product;

	@Builder
	public Stop(Long isStop, String reason, Product product) {
		this.isStop = isStop;
		this.reason = reason;
		this.product = product;
	}

	public void update(Long isStop, String reason) {
		this.isStop = isStop;
		this.reason = reason;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
