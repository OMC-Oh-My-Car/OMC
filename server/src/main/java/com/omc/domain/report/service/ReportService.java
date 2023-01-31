package com.omc.domain.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.omc.domain.img.dto.ImgDto;
import com.omc.domain.img.entity.ReportImg;
import com.omc.domain.member.entity.Member;
import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.product.entity.Product;
import com.omc.domain.product.service.ProductService;
import com.omc.domain.report.dto.ReportDto;
import com.omc.domain.report.entity.Report;
import com.omc.domain.report.repository.ReportRepository;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;
import com.omc.global.util.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

	private final ReportRepository reportRepository;

	private final ProductService productService;

	private final S3Service s3Service;

	public void create(ProductDto.Request req,
					   List<MultipartFile> multipartFiles,
					   Long productId,
					   Member member) {

		List<ReportImg> reportImgs = uploadImgAndImgDtoToEntity(multipartFiles);

		Report report = Report.builder()
							  .subject(req.getSubject())
							  .content(req.getDescription())
							  .status(1L)
							  .reportImgList(reportImgs)
							  .member(member)
							  .build();

		Product findProduct = productService.ifExistReturnProduct(productId);
		report.setProduct(findProduct);

		reportRepository.save(report);
	}

	private List<ReportImg> uploadImgAndImgDtoToEntity(List<MultipartFile> multipartFiles) {
		List<ReportImg> reportImgs = new ArrayList<>();
		List<ImgDto.Request> imgDtoList = new ArrayList<>();

		for (MultipartFile img : multipartFiles) {
			ImgDto.Request imgDto = s3Service.uploadImage(img, "report");
			imgDtoList.add(imgDto);
		}
		imgDtoToImg(reportImgs, imgDtoList);

		return reportImgs;
	}

	private static void imgDtoToImg(List<ReportImg> productImgList, List<ImgDto.Request> imgDtoList) {
		for (ImgDto.Request imgDto : imgDtoList) {
			ReportImg productImg = ReportImg.builder()
											.imgName(imgDto.getImgName())
											.imgUrl(imgDto.getImgUrl())
											.build();
			productImgList.add(productImg);
		}
	}

	public ReportDto.Response getReport(Long reportId) {

		Report findReport = reportRepository.findById(reportId)
											.orElseThrow(() -> new BusinessException(ErrorCode.REPORT_NOT_FOUND));

		return ReportDto.Response.builder()
								 .reportId(findReport.getId())
								 .reporter(findReport.getMember().getEmail())
								 .productImg(findReport.getProduct()
													   .getProductImgList()
													   .get(0)
													   .getImgUrl())
								 .createTime(findReport.getCreatedAt())
								 .subject(findReport.getSubject())
								 .content(findReport.getContent())
								 .status(findReport.getStatus())
								 .build();
	}

	public Page<Report> getReportList(ReportDto.Page request) {
		Pageable pageable = PageRequest.of(Math.toIntExact(request.getPage() - 1),
										   Math.toIntExact(request.getSize()),
										   Sort.by(request.getSort()).descending());

		if (request.getFilter() == null) {
			return reportRepository.findAll(pageable);
		} else {
			return reportRepository.findAllByStatus(request.getFilter(), pageable);
		}
	}

	public List<ReportDto.Response> convertToResponse(List<Report> content) {

		List<ReportDto.Response> responseList = new ArrayList<>();

		for (Report report : content) {
			ReportDto.Response response = ReportDto.Response.builder()
															.reportId(report.getId())
															.reporter(report.getMember().getEmail())
															.productImg(report.getProduct()
																			  .getProductImgList()
																			  .get(0)
																			  .getImgUrl())
															.createTime(report.getCreatedAt())
															.subject(report.getSubject())
															.content(report.getContent())
															.status(report.getStatus())
															.build();
			responseList.add(response);
		}

		return responseList;
	}
}
