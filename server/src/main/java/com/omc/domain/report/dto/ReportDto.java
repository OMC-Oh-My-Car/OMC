package com.omc.domain.report.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReportDto {

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Request {

		@NotBlank(message = "신고 제목을 입력해주세요.")
		@Size(min = 1, max = 30, message = "신고 제목은 1자 이상 30자 이하로 입력해주세요.")
		private String subject;

		@NotBlank(message = "신고 내용을 입력해주세요.")
		@Size(min = 1, max = 100, message = "신고 내용은 1자 이상 100자 이하로 입력해주세요.")
		private String content;
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private Long reportId;
		private String reporter;
		private String productImg;
		private LocalDateTime createTime;
		private String subject;
		private String content;
		private Long status;
		private String reportImg;

		@Builder
		public Response(Long reportId,
						String reporter,
						String productImg,
						LocalDateTime createTime,
						String subject,
						String content,
						Long status,
						String reportImg) {
			this.reportId = reportId;
			this.reporter = reporter;
			this.productImg = productImg;
			this.createTime = createTime;
			this.subject = subject;
			this.content = content;
			this.status = status;
			this.reportImg = reportImg;
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Page {
		private Long page;
		private Long size;
		private String sort;
		private Long filter;

		@Builder
		public Page(@RequestParam(value = "page") Long page,
					@RequestParam(value = "sort", required = false) String sort,
					@RequestParam(value = "f", required = false) Long filter) {
			this.page = page == null ? 1 : page;
			this.size = 20L;
			this.sort = sort == null ? "id" : sort;
			this.filter = filter;
		}
	}

}
