package com.omc.domain.reservation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.omc.domain.cashlog.service.CashLogService;
import com.omc.domain.member.service.MemberService;
import com.omc.global.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omc.domain.cancel.dto.CancelDto;
import com.omc.domain.cancel.entity.Cancel;
import com.omc.domain.cancel.service.CancelService;
import com.omc.domain.member.entity.Member;
import com.omc.domain.product.entity.Product;
import com.omc.domain.product.repository.ProductRepository;
import com.omc.domain.product.service.ProductService;
import com.omc.domain.reservation.dto.ReservationDto;
import com.omc.domain.reservation.entity.Reservation;
import com.omc.domain.reservation.repository.ReservationRepository;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final MemberService memberService;
    private final CancelService cancelService;
    private final ProductRepository productRepository;
    private final CashLogService cashLogService;
    private final Util util;

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
        LocalDateTime resCheckIn = LocalDateTime.of(LocalDate.parse(request.getStartDate()), LocalTime.parse(product.getCheckIn()));
        LocalDateTime resCheckOut = LocalDateTime.of(LocalDate.parse(request.getEndDate()), LocalTime.parse(product.getCheckOut()));

        // 현재보다 과거는 불가능
//        if (LocalDateTime.now().isBefore(resCheckIn)) {
//            throw new BusinessException(ErrorCode.NOT_YET_CHECKIN);
//        }

        long countRes = reservationRepository.countByCheckInBetween(request.getProductId(), resCheckIn);
        if (countRes > 0) {
            throw new BusinessException(ErrorCode.CANT_RESERVATION);
        }

        // 결제 후 예약 추가

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

    public ReservationDto.Response getResponseDto(long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            throw new BusinessException(ErrorCode.RESERVATION_NOT_FOUND);
        }
        ReservationDto.Response responseDto = toResponseDto(reservation);

        return responseDto;
    }

    public Page<Reservation> getReservationPages(ReservationDto.Search search) {
        Pageable pageable = PageRequest.of(Math.toIntExact(search.getPage() - 1),
                Math.toIntExact(search.getSize()),
                Sort.by("id").descending());
        Page<Reservation> reservationPage = reservationRepository.findAllByOrderByIdDesc(pageable);

        return reservationPage;
    }

    private ReservationDto.Response toResponseDto(Reservation reservation) {
        ReservationDto.Response responseDto = ReservationDto.Response.builder()
                .reservationId(reservation.getUniqueId())
                .phoneNumber(reservation.getPhoneNumber())
                .checkIn(util.convertReviewLocalDateTime(reservation.getCheckIn()))
                .checkOut(util.convertReviewLocalDateTime(reservation.getCheckOut()))
                .isCancel(reservation.getIsCancel())
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

        if (reservation.getMember().getId() != member.getId()){
            throw new BusinessException(ErrorCode.NO_PERMISSION);
        }

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
}
