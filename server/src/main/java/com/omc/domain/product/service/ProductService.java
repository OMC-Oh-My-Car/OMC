package com.omc.domain.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.omc.domain.img.dto.ImgDto;
import com.omc.domain.img.entity.Img;
import com.omc.domain.img.repository.ImgRepository;
import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.product.entity.Facilities;
import com.omc.domain.product.entity.Location;
import com.omc.domain.product.entity.Product;
import com.omc.domain.product.repository.FacilitiesRepository;
import com.omc.domain.product.repository.LocationRepository;
import com.omc.domain.product.repository.ProductRepository;
import com.omc.global.util.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

	// private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	private final FacilitiesRepository facilitiesRepository;
	private final LocationRepository locationRepository;
	private final ImgRepository imgRepository;

	private final S3Service s3Service;

	/**
	 * 상품 등록
	 * @param post: 상품 정보
	 *                     - subject: 상품명
	 *                     - description: 상품 설명
	 * @param multipartFiles: 상품 이미지
	 */
	@SneakyThrows
	@Transactional
	public void uploadProduct(ProductDto.Post post, List<MultipartFile> multipartFiles) {

		log.info("multipartFile imgs={}", multipartFiles);
		String address = post.getAddress();

		List<Img> imgList = new ArrayList<>();
		List<ImgDto.Request> imgDtoList = new ArrayList<>();

		if (multipartFiles != null) {
			for (MultipartFile img : multipartFiles) {
				ImgDto.Request imgDto = s3Service.uploadImage(img, "product");
				imgDtoList.add(imgDto);
			}
		}

		imgDtoToImg(imgList, imgDtoList);

		Product product = Product.builder()
			.subject(post.getSubject())
			.description(post.getDescription())
			.imgList(imgList)
			.address(address)
			.zipcode(post.getZipcode())
			.price(post.getPrice())
			.telephone(post.getTelephone())
			.checkIn(post.getCheckIn())
			.checkOut(post.getCheckOut())
			.likes(0L)
			.star(0.0)
			.reportCount(0L)
			.count(0L)
			.build();

		productRepository.save(product);

		StringTokenizer st = new StringTokenizer(post.getFacilities(), "#");

		List<String> keyword = new ArrayList<>();
		while (st.hasMoreTokens()) {
			keyword.add(st.nextToken());
		}

		facilitiesRepository.saveAll(
			keyword.stream()
				.map(facility -> Facilities.builder()
					.product(product)
					.keyword(facility)
					.build())
				.collect(Collectors.toList())
		);

		st = new StringTokenizer(address, " ");

		List<String> locations = new ArrayList<>();
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (s.endsWith("도") || s.endsWith("시") ||
				s.endsWith("군") || s.endsWith("구") ||
				s.endsWith("읍") || s.endsWith("면") ||
				s.endsWith("리") || s.endsWith("로") ||
				s.endsWith("길") || s.endsWith("동") ||
				s.endsWith("가") || s.endsWith("번지")) {
				locations.add(s);
			}
		}

		locationRepository.saveAll(
			locations.stream()
				.map(location -> Location.builder()
					.product(product)
					.keyword(location)
					.build())
				.collect(Collectors.toList())
		);

	}

	/**
	 * ImgDto를 Img로 변환
	 * @param imgList : Img 리스트
	 * @param imgDtoList : ImgDto 리스트
	 */
	private static void imgDtoToImg(List<Img> imgList, List<ImgDto.Request> imgDtoList) {
		for (ImgDto.Request imgDto : imgDtoList) {
			Img img = Img.builder()
				.imgName(imgDto.getImgName())
				.imgUrl(imgDto.getImgUrl())
				.build();
			imgList.add(img);
		}
	}
}
