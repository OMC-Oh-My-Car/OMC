package com.omc.domain.report.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.omc.domain.img.entity.ProductImg;
import com.omc.domain.img.entity.ReportImg;
import com.omc.domain.product.entity.Product;
import com.omc.global.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Report extends BaseEntity {

	@Column
	private String subject;

	@Column
	private String content;

	@Column
	private Long status; // 0: 신고완료, 1: 처리완료

	@OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
	private List<ReportImg> reportImgList = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Builder
	public Report(String subject, String content, Long status, List<ReportImg> reportImgList) {
		this.subject = subject;
		this.content = content;
		this.status = status;
		this.reportImgList = reportImgList;
		for (ReportImg reportImg : reportImgList) {
			reportImg.setReport(this);
		}
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
