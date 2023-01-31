package com.omc.domain.report.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omc.domain.member.entity.Member;
import com.omc.domain.member.service.MemberService;
import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.report.service.ReportService;
import com.omc.global.common.annotation.CurrentMember;
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
	public ResponseEntity<?> create(@RequestPart(value = "report") ProductDto.Request req,
									@RequestPart(value = "imgUrl") List<MultipartFile> multipartFiles,
									@PathVariable Long productId,
									@CurrentMember Member member) {

		if (multipartFiles == null) {
			log.error("multipartFiles is null");
			throw new BusinessException(ErrorCode.IMAGE_NOT_FOUND);
		}

		Optional<Member> findMember = memberService.findByEmail(member.getEmail());

		if (!findMember.isPresent()) {
			log.error("member is not found");
			throw new BusinessException(ErrorCode.MEMBER_NOT_EXISTS);
		}

		reportService.create(req, multipartFiles, productId, findMember.get());

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
