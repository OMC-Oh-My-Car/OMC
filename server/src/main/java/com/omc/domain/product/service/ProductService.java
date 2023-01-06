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
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;
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

	private StringTokenizer st;

	/**
	 * 상품 등록
	 * @param req: 상품 정보
	 * @param multipartFiles: 상품 이미지
	 */
	@SneakyThrows
	@Transactional
	public void create(ProductDto.Request req, List<MultipartFile> multipartFiles) {

		log.info("multipartFile imgs={}", multipartFiles);
		String address = req.getAddress();

		List<Img> imgList = uploadImgAndImgDtoToEntity(multipartFiles);

		Product product = Product.builder()
			.subject(req.getSubject())
			.description(req.getDescription())
			.imgList(imgList)
			.address(address)
			.zipcode(req.getZipcode())
			.price(req.getPrice())
			.telephone(req.getTelephone())
			.checkIn(req.getCheckIn())
			.checkOut(req.getCheckOut())
			.likes(0L)
			.star(0.0)
			.reportCount(0L)
			.count(0L)
			.build();

		saveLocation(product, req);
		saveFacilities(product, req);

		productRepository.save(product);

	}

	/**
	 * 상품 수정
	 * @param req : 상품 정보
	 * @param multipartFiles : 상품 이미지
	 * @param productId : 상품 id
	 */
	@Transactional
	public void update(ProductDto.Request req, List<MultipartFile> multipartFiles, Long productId) {

		Product findProduct = ifExistReturnProduct(productId);
		findProduct.editProduct(req);

		if (req.getFacilities() != null) {
			facilitiesRepository.deleteByProductId(productId);
			saveFacilities(findProduct, req);
		}
		if (req.getAddress() != null) {
			locationRepository.deleteByProductId(productId);
			saveLocation(findProduct, req);
		}

		if (multipartFiles != null) {
			findProduct.getImgList().stream().map(Img::getImgName).forEach(s3Service::deleteFile);
			imgRepository.deleteByProductId(productId);
			List<Img> imgs = uploadImgAndImgDtoToEntity(multipartFiles);
			findProduct.setImgList(imgs);
		}

		productRepository.save(findProduct);
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

	/**
	 * 상품 이미지 업로드 및 ImgDto를 Img로 변환
	 * @param multipartFiles : 상품 이미지
	 * @return : 이미지 리스트
	 */
	private List<Img> uploadImgAndImgDtoToEntity(List<MultipartFile> multipartFiles) {
		List<Img> imgList = new ArrayList<>();
		List<ImgDto.Request> imgDtoList = new ArrayList<>();

		for (MultipartFile img : multipartFiles) {
			ImgDto.Request imgDto = s3Service.uploadImage(img, "product");
			imgDtoList.add(imgDto);
		}
		imgDtoToImg(imgList, imgDtoList);

		return imgList;
	}

	/**
	 * 상품 위치 저장
	 * @param product : 상품
	 * @param req : 상품 정보
	 */
	private void saveLocation(Product product, ProductDto.Request req) {

		st = new StringTokenizer(req.getAddress(), " ");

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
	 * 상품 편의시설 저장
	 * @param product : 상품
	 * @param req : 상품 정보
	 */
	private void saveFacilities(Product product, ProductDto.Request req) {

		st = new StringTokenizer(req.getFacilities(), "#");

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
	}

	/**
	 * 상품 존재 여부 확인
	 * @param productId : 상품 id
	 * @return : 상품
	 */
	private Product ifExistReturnProduct(Long productId) {
		return productRepository.findById(productId)
			.orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
	}
}
