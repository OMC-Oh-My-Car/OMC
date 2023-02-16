package com.omc.domain.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.omc.domain.img.dto.ImgDto;
import com.omc.domain.img.entity.ProductImg;
import com.omc.domain.img.repository.ProductImgRepository;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.entity.UserRole;
import com.omc.domain.product.dto.ProductDto;
import com.omc.domain.product.dto.StopDto;
import com.omc.domain.product.entity.Facilities;
import com.omc.domain.product.entity.LikeHistory;
import com.omc.domain.product.entity.Location;
import com.omc.domain.product.entity.Product;
import com.omc.domain.product.entity.StopHistory;
import com.omc.domain.product.repository.FacilitiesRepository;
import com.omc.domain.product.repository.LikeHistoryRepository;
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

	private final ProductRepository productRepository;
	private final FacilitiesRepository facilitiesRepository;
	private final LocationRepository locationRepository;
	private final ProductImgRepository productImgRepository;
	private final LikeHistoryRepository likeHistoryRepository;

	private final S3Service s3Service;

	private StringTokenizer st;

	/**
	 * 상품 등록
	 *
	 * @param req:            상품 정보
	 * @param multipartFiles: 상품 이미지
	 */
	@SneakyThrows
	@Transactional
	public void create(ProductDto.Request req,
					   List<MultipartFile> multipartFiles,
					   Member member) {

		log.info("multipartFile imgs={}", multipartFiles);
		String address = req.getAddress();

		List<ProductImg> productImgList = uploadImgAndImgDtoToEntity(multipartFiles);

		Product product = Product.builder()
								 .subject(req.getSubject())
								 .description(req.getDescription())
								 .productImgList(productImgList)
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
								 .member(member)
								 .build();

		saveLocation(product, req);
		saveFacilities(product, req);

		productRepository.save(product);

	}

	/**
	 * 상품 수정
	 *
	 * @param req            : 상품 정보
	 * @param multipartFiles : 상품 이미지
	 * @param productId      : 상품 id
	 */
	@Transactional
	public void update(ProductDto.Request req,
					   List<MultipartFile> multipartFiles,
					   Long productId,
					   Member member) {

		Product findProduct = ifExistReturnProduct(productId);

		if (!findProduct.getMember().getId().equals(member.getId()) || !member.getUserRole()
																			  .equals(UserRole.ROLE_ADMIN)) {
			throw new BusinessException(ErrorCode.NOT_PRODUCT_WRITER);
		}

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
			findProduct.getProductImgList().stream().map(ProductImg::getImgName).forEach(s3Service::deleteFile);
			productImgRepository.deleteByProductId(productId);
			List<ProductImg> productImgs = uploadImgAndImgDtoToEntity(multipartFiles);
			findProduct.setProductImgList(productImgs);
		}

		productRepository.save(findProduct);
	}

	/**
	 * 상품 상세 조회
	 *
	 * @param productId : 상품 id
	 * @return 상품 정보
	 */
	@Transactional
	public ProductDto.Response getProduct(Long productId, Member member) {
		Product findProduct = productRepository.findById(productId)
											   .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

		Optional<LikeHistory> likeHistory = Optional.empty();

		if (member != null) {
			likeHistory = likeHistoryRepository.findByMemberIdAndProductId(member.getId(),
																		   findProduct.getId());
		}

		findProduct.addViews();

		return ProductDto.Response.builder()
								  .id(findProduct.getId())
								  .subject(findProduct.getSubject())
								  .description(findProduct.getDescription())
								  .address(findProduct.getAddress())
								  .zipcode(findProduct.getZipcode())
								  .locations(getLocations(productId))
								  .facilities(getFacilities(productId))
								  .reportCount(findProduct.getReportCount())
								  .telephone(findProduct.getTelephone())
								  .price(findProduct.getPrice())
								  .star(findProduct.getStar())
								  .checkIn(findProduct.getCheckIn())
								  .checkOut(findProduct.getCheckOut())
								  .img(getImgs(productId))
								  .likes(findProduct.getLikes())
								  .isLike(likeHistory.isPresent())
								  .build();
	}

	/**
	 * 상품 목록 조회 (검색어, 편의시설)
	 *
	 * @param search : 검색어
	 * @return 상품 목록
	 */
	@Transactional
	public Page<Product> getProductList(ProductDto.Search search) {
		// todo 리팩터링

		String sortBy = switch (search.getSort()) { // 최신순, 인기순, 조회순, 추천순
			case "0" -> "id"; // 최신순
			case "1" -> "star"; // 인기순
			case "2" -> "views"; // 조회순
			case "3" -> "likes"; // 추천순
			default -> "id";
		};

		Pageable pageable = PageRequest.of(Math.toIntExact(search.getPage() - 1),
										   Math.toIntExact(search.getSize()),
										   Sort.by(sortBy).descending());

		String searchQuery = search.getQuery() == null ? "null" : search.getQuery();

		if (search.getFacilities() != null && !searchQuery.equals("null")) { // 편의시설, 검색어
			List<String> keywords = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(search.getFacilities(), "#");
			while (st.hasMoreTokens()) {
				keywords.add(st.nextToken());
			}

			List<Long> productIds = new ArrayList<>();

			for (String keyword : keywords) {
				List<Facilities> allByKeywordContaining = facilitiesRepository.findAllByKeywordContaining(keyword);
				allByKeywordContaining.stream().map(s -> s.getProduct().getId()).forEach(productIds::add);
			}
			;

			return productRepository.findBySubjectContainingOrDescriptionContainingAndIdIn(searchQuery,
																						   searchQuery,
																						   productIds,
																						   pageable);
		} else if (search.getFacilities() == null && !searchQuery.equals("null")) { // 검색어
			return productRepository.findBySubjectContainingOrDescriptionContaining(searchQuery,
																					searchQuery,
																					pageable);
		} else if (search.getFacilities() != null) { // 편의시설
			List<String> keywords = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(search.getFacilities(), "#");
			while (st.hasMoreTokens()) {
				keywords.add(st.nextToken());
			}

			List<Long> productIds = new ArrayList<>();

			for (String keyword : keywords) {
				List<Facilities> allByKeywordContaining = facilitiesRepository.findAllByKeywordContaining(keyword);
				allByKeywordContaining.stream().map(s -> s.getProduct().getId()).forEach(productIds::add);
			}
			;
			return productRepository.findByIdIn(productIds, pageable);
		} else { // 검색어, 편의시설 없음
			return productRepository.findAll(pageable);
		}
	}

	/**
	 * 등록한 상품 조회
	 *
	 * @param member : 회원 (판매자 or 관리자)
	 * @param search : Pageable
	 * @return : 상품 목록
	 */
	@Transactional
	public Page<Product> getMyProductList(Member member, ProductDto.Search search) {

		if (member.getUserRole() == UserRole.ROLE_USER) {
			throw new BusinessException(ErrorCode.NOT_PRODUCT_WRITER);
		}

		String sortBy = switch (search.getSort()) { // 최신순, 인기순, 조회순, 추천순
			case "0" -> "id"; // 최신순
			case "1" -> "star"; // 인기순
			case "2" -> "views"; // 조회순
			case "3" -> "likes"; // 추천순
			default -> "id";
		};

		Pageable pageable = PageRequest.of(Math.toIntExact(search.getPage() - 1),
										   Math.toIntExact(search.getSize()),
										   Sort.by(sortBy).descending());
		return productRepository.findAllByMemberId(member.getId(), pageable);
	}

	/**
	 * 상품 삭제
	 *
	 * @param productId : 상품 id
	 */
	@Transactional
	public void delete(Long productId, Member member) {
		Product findProduct = ifExistReturnProduct(productId);
		findProduct.getProductImgList().stream().map(ProductImg::getImgName).forEach(s3Service::deleteFile);

		if (!findProduct.getMember().getId().equals(member.getId()) || !member.getUserRole()
																			  .equals(UserRole.ROLE_ADMIN)) {
			throw new BusinessException(ErrorCode.NOT_PRODUCT_WRITER);
		}

		findProduct.getProductImgList().stream().map(ProductImg::getImgName).forEach(s3Service::deleteFile);
		productRepository.delete(findProduct);
	}

	/**
	 * 상품 추천
	 *
	 * @param productId : 상품 id
	 * @param member    : 회원
	 */
	@Transactional
	public void likeProduct(Long productId, Member member) {
		Product product = ifExistReturnProduct(productId);

		Optional<LikeHistory> likeHistory = likeHistoryRepository.findByMemberIdAndProductId(member.getId(),
																							 product.getId());
		if (likeHistory.isPresent()) {
			likeHistoryRepository.delete(likeHistory.get());
			product.disLike();
		} else {
			likeHistoryRepository.save(LikeHistory.builder()
												  .member(member)
												  .product(product)
												  .build());
			product.like();
		}
	}

	/**
	 * 상품 상태 관리
	 *
	 * @param productId : 상품 id
	 * @param req       :
	 *                  - isStop : 0: 판매중, 1: 판매중지, 2: 블라인드
	 *                  - stopReason : 상품 상태 변경 사유
	 * @param member    : 로그인한 회원
	 * @return 상품 정보
	 */
	@Transactional
	public StopDto.Response setStatus(Long productId, StopDto.Request req, Member member) {
		Product product = ifExistReturnProduct(productId);
		UserRole userRole = member.getUserRole();

		if (!product.getMember().getId().equals(member.getId()) || userRole != UserRole.ROLE_ADMIN) {
			throw new BusinessException(ErrorCode.NOT_PRODUCT_WRITER);
		}

		if (product.getIsStop() == 2) {
			throw new BusinessException(ErrorCode.FORBIDDEN_ADMIN);
		}

		ArrayList<StopHistory> stopHistories = new ArrayList<>();
		StopHistory stopHistory = StopHistory.builder()
											 .product(product)
											 .isStop(req.getIsStop())
											 .reason(req.getStopReason())
											 .build();
		stopHistories.add(stopHistory);
		product.setIsStop(req.getIsStop());
		product.addStopHistory(stopHistories);
		productRepository.save(product);

		return StopDto.Response.builder()
							   .productId(productId)
							   .subject(product.getSubject())
							   .description(product.getDescription())
							   .locations(getLocations(productId))
							   .reportCount(product.getReportCount())
							   .price(product.getPrice())
							   .star(product.getStar())
							   .img(getImgs(productId))
							   .likes(product.getLikes())
							   .isStop(stopHistory.getIsStop())
							   .stopReason(stopHistory.getReason())
							   .build();
	}

	/**
	 * 상품의 이미지 조회
	 *
	 * @param productId : 상품 id
	 * @return 상품 이미지 목록
	 */
	private List<String> getImgs(Long productId) {
		return productImgRepository.findAllByProductId(productId).stream()
								   .map(ProductImg::getImgUrl)
								   // .map(s -> "value\":\"" + s)
								   .toList();
	}

	/**
	 * 상품의 편의시설 조회
	 *
	 * @param productId : 상품 id
	 * @return 상품 편의시설 목록
	 */
	private List<String> getFacilities(Long productId) {
		return facilitiesRepository.findAllByProductId(productId).stream()
								   .map(Facilities::getKeyword)
								   // .map(s -> "value\":\"" + s)
								   .toList();
	}

	/**
	 * 상품의 위치 키워드 조회
	 *
	 * @param productId : 상품 id
	 * @return 상품 위치 키워드 목록
	 */
	private List<String> getLocations(Long productId) {
		return locationRepository.findAllByProductId(productId).stream()
								 .map(Location::getKeyword)
								 // .map(s -> "value\":\"" + s)
								 .toList();
	}

	/**
	 * ImgDto를 Img로 변환
	 *
	 * @param productImgList : ProductImg 리스트
	 * @param imgDtoList     : ImgDto 리스트
	 */
	private static void imgDtoToImg(List<ProductImg> productImgList, List<ImgDto.Request> imgDtoList) {
		for (ImgDto.Request imgDto : imgDtoList) {
			ProductImg productImg = ProductImg.builder()
											  .imgName(imgDto.getImgName())
											  .imgUrl(imgDto.getImgUrl())
											  .build();
			productImgList.add(productImg);
		}
	}

	/**
	 * 상품 이미지 업로드 및 ImgDto를 Img로 변환
	 *
	 * @param multipartFiles : 상품 이미지
	 * @return : 이미지 리스트
	 */
	private List<ProductImg> uploadImgAndImgDtoToEntity(List<MultipartFile> multipartFiles) {
		List<ProductImg> productImgList = new ArrayList<>();
		List<ImgDto.Request> imgDtoList = new ArrayList<>();

		for (MultipartFile img : multipartFiles) {
			ImgDto.Request imgDto = s3Service.uploadImage(img, "product");
			imgDtoList.add(imgDto);
		}
		imgDtoToImg(productImgList, imgDtoList);

		return productImgList;
	}

	/**
	 * 상품 위치 저장
	 *
	 * @param product : 상품
	 * @param req     : 상품 정보
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
	 *
	 * @param product : 상품
	 * @param req     : 상품 정보
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
	 *
	 * @param productId : 상품 id
	 * @return : 상품
	 */
	public Product ifExistReturnProduct(Long productId) {
		return productRepository.findById(productId)
								.orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
	}

	/**
	 * Product to ResponseDto
	 *
	 * @param content : 상품 목록 (Product)
	 * @return : 상품 목록 (ResponseDto)
	 */
	public List<ProductDto.Response> convertToResponse(List<Product> content) {

		List<ProductDto.Response> responseList = new ArrayList<>();

		for (Product product : content) {
			responseList.add(ProductDto.Response.builder()
												.id(product.getId())
												.subject(product.getSubject())
												.description(product.getDescription())
												.address(product.getAddress())
												.zipcode(product.getZipcode())
												.reportCount(product.getReportCount())
												.telephone(product.getTelephone())
												.price(product.getPrice())
												.star(product.getStar())
												.checkIn(product.getCheckIn())
												.checkOut(product.getCheckOut())
												.likes(product.getLikes())
												.locations(getLocations(product.getId()))
												.facilities(getFacilities(product.getId()))
												.img(getImgs(product.getId()))
												.build());
		}
		return responseList;
	}

}
