package com.omc.domain.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.omc.global.common.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
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
}
