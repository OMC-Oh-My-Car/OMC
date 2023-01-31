package com.omc.domain.report.dto;



import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReportDto {

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

		@Builder
		public Response(Long reportId,
						String reporter,
						String productImg,
						LocalDateTime createTime,
						String subject,
						String content,
						Long status) {
			this.reportId = reportId;
			this.reporter = reporter;
			this.productImg = productImg;
			this.createTime = createTime;
			this.subject = subject;
			this.content = content;
			this.status = status;
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
