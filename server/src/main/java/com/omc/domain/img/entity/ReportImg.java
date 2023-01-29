package com.omc.domain.img.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.omc.domain.report.entity.Report;
import com.omc.global.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ReportImg extends BaseEntity {

	@Column(nullable = false)
	private String imgUrl;

	@Column(nullable = false)
	private String imgName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Report report;

	@Builder
	public ReportImg(String imgUrl, Report report, String imgName) {
		this.imgUrl = imgUrl;
		this.report = report;
		this.imgName = imgName;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}
