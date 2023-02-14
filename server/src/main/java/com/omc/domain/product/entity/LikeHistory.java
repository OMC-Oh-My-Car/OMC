package com.omc.domain.product.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.omc.domain.member.entity.Member;
import com.omc.global.common.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class LikeHistory extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Builder
	public LikeHistory(Member member, Product product) {
		this.member = member;
		this.product = product;
	}

}
