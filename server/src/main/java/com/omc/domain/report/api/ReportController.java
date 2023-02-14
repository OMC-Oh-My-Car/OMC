package com.omc.domain.report.api;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.service.MemberService;
import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.report.dto.ReportDto;
import com.omc.domain.report.entity.Report;
import com.omc.domain.report.service.ReportService;
import com.omc.global.common.annotation.CurrentMember;
import com.omc.global.common.dto.MultiResponse;
import com.omc.global.common.dto.SingleResponseDto;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReportController {

	private final ReportService reportService;

	private final MemberService memberService;

	@PostMapping(value = "/report/{productId}",
				 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> create(@RequestPart(value = "report") ReportDto.Request req,
									@RequestPart(value = "imgUrl") List<MultipartFile> multipartFiles,
									@PathVariable Long productId,
									@CurrentMember AuthMember member) {

		Member findMember = memberService.findByEmail(member.getEmail())
										 .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

		if (multipartFiles == null) {
			log.error("multipartFiles is null");
			throw new BusinessException(ErrorCode.IMAGE_NOT_FOUND);
		}

		reportService.create(req, multipartFiles, productId, findMember);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/report/{reportId}")
	public ResponseEntity<?> getReport(@PathVariable Long reportId) {

		ReportDto.Response res = reportService.getReport(reportId);

		return new ResponseEntity<>(new SingleResponseDto<>(res), HttpStatus.OK);
	}

	@GetMapping("/report")
	public ResponseEntity<?> getReportList(@ModelAttribute ReportDto.Page request) {

		Page<Report> resPage = reportService.getReportList(request);
		List<ReportDto.Response> res = reportService.convertToResponse(resPage.getContent());

		return new ResponseEntity<>(new MultiResponse<>(res, resPage), HttpStatus.OK);
	}
}
