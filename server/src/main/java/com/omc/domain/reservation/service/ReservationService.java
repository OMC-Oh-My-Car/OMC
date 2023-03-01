package com.omc.domain.reservation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.reservation.dto.ReservationSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omc.domain.cancel.dto.CancelDto;
import com.omc.domain.cancel.entity.Cancel;
import com.omc.domain.cancel.service.CancelService;
import com.omc.domain.cashlog.service.CashLogService;
import com.omc.domain.img.entity.ProductImg;
import com.omc.domain.img.repository.ProductImgRepository;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.entity.UserRole;
import com.omc.domain.member.repository.MemberRepository;
import com.omc.domain.member.service.MemberService;
import com.omc.domain.product.entity.Product;
import com.omc.domain.product.repository.ProductRepository;
import com.omc.domain.reservation.dto.ReservationDto;
import com.omc.domain.reservation.entity.Reservation;
import com.omc.domain.reservation.entity.ReservationStatus;
import com.omc.domain.reservation.repository.ReservationRepository;
import com.omc.domain.review.entity.Review;
import com.omc.domain.review.repository.ReviewRepository;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;
import com.omc.global.util.Util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final ReviewRepository reviewRepository;
	private final MemberService memberService;
	private final CancelService cancelService;
	private final ProductRepository productRepository;
	private final CashLogService cashLogService;
	private final Util ut;
	private final ProductImgRepository productImgRepository;
	private final MemberRepository memberRepository;

	public Reservation findById(long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
		return reservation;
	}

	@Transactional
	public void createReservation(ReservationDto.Request request, Member member) {
		Product product = productRepository.findById(request.getProductId()).orElse(null);

		if (product == null) {
			// 상품 존재하지 않아서 예외처리
			throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
		}
		LocalDateTime resCheckIn = LocalDateTime.of(LocalDate.parse(request.getStartDate()),
													LocalTime.parse(product.getCheckIn()));
		LocalDateTime resCheckOut = LocalDateTime.of(LocalDate.parse(request.getEndDate()),
													 LocalTime.parse(product.getCheckOut()));

		// 현재보다 과거는 불가능
		//        if (LocalDateTime.now().isBefore(resCheckIn)) {
		//            throw new BusinessException(ErrorCode.NOT_YET_CHECKIN);
		//        }

		long countRes = reservationRepository.countByCheckInBetween(request.getProductId(), resCheckIn, resCheckOut);
		if (countRes > 0) {
			throw new BusinessException(ErrorCode.CANT_RESERVATION);
		}

		Reservation reservation = Reservation.builder()
											 .member(member)
											 .product(product)
											 .checkIn(resCheckIn)
											 .checkOut(resCheckOut)
											 .phoneNumber(request.getPhoneNumber())
											 //                .startDate(LocalDate.parse(request.getStartDate()))
											 //                .endDate(LocalDate.parse(request.getEndDate()))
											 .isCancel(0)
											 .build();

		reservationRepository.save(reservation);
	}

	public ReservationDto.DetailDto getResponseDto(long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
		if (reservation == null) {
			throw new BusinessException(ErrorCode.RESERVATION_NOT_FOUND);
		}
		ReservationDto.DetailDto responseDto = toDetailResponseDto(reservation);

		return responseDto;
	}

	private ReservationDto.DetailDto toDetailResponseDto(Reservation reservation) {
		ProductImg productImg = productImgRepository.findFirstByProductId(reservation.getProduct().getId());
		Product product = productRepository.findById(reservation.getProduct().getId())
										   .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
		Member member = memberRepository.findById(reservation.getMember().getId())
										.orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

		ReservationDto.DetailDto responseDto = ReservationDto.DetailDto.builder()
																	   .reservationId(reservation.getId())
																	   .title(product.getSubject())
																	   .thumbNail(productImg.getImgUrl())
																	   .reservationCode(reservation.getUniqueId())
																	   .phoneNumber(reservation.getPhoneNumber())
																	   .checkIn(ut.convertLocalDateTimeFormat2(
																		   reservation.getCheckIn()))
																	   .checkOut(ut.convertLocalDateTimeFormat2(
																		   reservation.getCheckOut()))
																	   .isCancel(reservation.getIsCancel())
																	   .name(member.getUsername())
																	   .email(member.getEmail())
																	   .productId(product.getId())
																	   .build();

		return responseDto;
	}

	public Page<Reservation> getReservationPages(ReservationDto.Search search) {
		Pageable pageable = PageRequest.of(Math.toIntExact(search.getPage() - 1),
										   Math.toIntExact(search.getSize()),
										   Sort.by("id").descending());
		Page<Reservation> reservationPage = reservationRepository.findAllByOrderByIdDesc(pageable);

		return reservationPage;
	}

	public Page<Reservation> getMyReservationPages(ReservationDto.Search search, Member member) {
		Pageable pageable = PageRequest.of(Math.toIntExact(search.getPage() - 1),
				Math.toIntExact(search.getSize()),
				Sort.by("id").descending());
		Page<Reservation> reservationPage = reservationRepository.findAllByMemberOrderByIdDesc(pageable, member);

		return reservationPage;
	}

	private ReservationDto.Response toResponseDto(Reservation reservation) {
		ProductImg productImg = productImgRepository.findFirstByProductId(reservation.getProduct().getId());
		Product product = productRepository.findById(reservation.getProduct().getId())
										   .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
		Optional<Review> review = reviewRepository.findByReservationId(reservation.getId());
		Member member = memberRepository.findById(reservation.getMember().getId())
										.orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

		log.info("review : {}", review);

		ReservationDto.Response responseDto = ReservationDto.Response.builder()
																	 .reservationId(reservation.getId())
																	 .title(product.getSubject())
																	 .thumbNail(productImg.getImgUrl())
																	 .reservationCode(reservation.getUniqueId())
																	 .phoneNumber(reservation.getPhoneNumber())
																	 .checkIn(ut.convertLocalDateTimeFormat1(
																		 reservation.getCheckIn()))
																	 .checkOut(ut.convertLocalDateTimeFormat1(
																		 reservation.getCheckOut()))
																	 .isCancel(reservation.getIsCancel())
																	 .hasReview(review.isPresent())
																	 .status(
																		 reservation.getReservationStatus().toString())
																	 .email(member.getEmail())
																	 .build();

		return responseDto;
	}

	public CancelDto.Response getCancelReason(long reservationId) {
		Cancel cancel = cancelService.findByResId(reservationId);
		if (cancel == null) {
			throw new BusinessException(ErrorCode.CANCEL_NOT_FOUND);
		}
		if (cancel.getReason() == null) {
			throw new BusinessException(ErrorCode.RESERVATION_NOT_FOUND);
		}

		CancelDto.Response response = CancelDto.Response.builder()
														.cancelTime(cancel.getCreatedAt())
														.cancelReason(cancel.getReason())
														.build();

		return response;
	}

	@Transactional
	public void cancelReservation(long reservationId, CancelDto.Request request, Member member) {
		Reservation reservation = findById(reservationId);
		if (reservation == null) {
			throw new BusinessException(ErrorCode.RESERVATION_NOT_FOUND);
		}

		if (member.getUserRole() != UserRole.ROLE_ADMIN && member.getUserRole() != UserRole.ROLE_SELLER
			&& reservation.getMember().getId() != member.getId()) {
			throw new BusinessException(ErrorCode.NO_PERMISSION);
		}

		reservation.setReservationStatus(ReservationStatus.RESERVATION_CANCEL);

		// 취소 사유 생성
		reservation.setIsCancelOn(cancelService.createCancel(request, reservation, member));
		reservationRepository.save(reservation);
	}

	public Page<Reservation> getProductsReservationList(long productId, ReservationDto.Search search) {
		Pageable pageable = PageRequest.of(Math.toIntExact(search.getPage() - 1),
										   Math.toIntExact(search.getSize()),
										   Sort.by(search.getSort()).descending());
		Page<Reservation> reservationPage = reservationRepository.findAllByProductId(productId, pageable);

		return reservationPage;
	}

	public List<ReservationDto.Response> pageToResponseList(List<Reservation> reservationList) {
		return reservationList.stream().map(reservation -> toResponseDto(reservation)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ReservationDto.CanReservationRes canReservation(ReservationDto.Request request) {
		Product product = productRepository.findById(request.getProductId()).orElse(null);

		if (product == null) {
			// 상품 존재하지 않아서 예외처리
			throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
		}

		LocalDateTime resCheckIn = LocalDateTime.of(LocalDate.parse(request.getStartDate()),
				LocalTime.parse(product.getCheckIn()));
		LocalDateTime resCheckOut = LocalDateTime.of(LocalDate.parse(request.getEndDate()),
				LocalTime.parse(product.getCheckOut()));

		// 현재보다 과거는 불가능
		//        if (LocalDateTime.now().isBefore(resCheckIn)) {
		//            throw new BusinessException(ErrorCode.NOT_YET_CHECKIN);
		//        }

		List<Reservation> reservationList = reservationRepository.findByCheckInBetween(request.getProductId(), resCheckIn, resCheckOut);
		ReservationDto.CanReservationRes canReservationRes;
		if (reservationList.size() > 0) {
			List<ReservationSimpleDto> reservationSimpleDtoList = resToSimpleDto(reservationList);
			canReservationRes = ReservationDto.CanReservationRes.builder()
					.resCount(reservationSimpleDtoList.size())
					.reservationList(reservationSimpleDtoList)
					.build();
		} else {
			canReservationRes = ReservationDto.CanReservationRes.builder()
					.resCount(0)
					.reservationList(null)
					.build();
		}

		return canReservationRes;
	}

	public List<ReservationSimpleDto> resToSimpleDto(List<Reservation> reservationList) {
		return reservationList.stream().map(reservation -> toSimpleDto(reservation)).collect(Collectors.toList());
	}

	private ReservationSimpleDto toSimpleDto(Reservation reservation) {

		return ReservationSimpleDto.builder()
				.checkIn(ut.convertLocalDateTimeFormat2(
						reservation.getCheckIn()))
				.checkOut(ut.convertLocalDateTimeFormat2(
						reservation.getCheckOut()))
				.build();
	}


}
