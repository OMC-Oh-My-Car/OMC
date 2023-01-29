package com.omc.domain.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.omc.domain.img.dto.ImgDto;
import com.omc.domain.img.entity.ProductImg;
import com.omc.domain.img.entity.ReportImg;
import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.product.entity.Product;
import com.omc.domain.product.service.ProductService;
import com.omc.domain.report.entity.Report;
import com.omc.domain.report.repository.ReportRepository;
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
					   Long productId) {

		List<ReportImg> reportImgs = uploadImgAndImgDtoToEntity(multipartFiles);

		Report report = Report.builder()
							  .subject(req.getSubject())
							  .content(req.getDescription())
							  .status(1L)
							  .reportImgList(reportImgs)
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
}
