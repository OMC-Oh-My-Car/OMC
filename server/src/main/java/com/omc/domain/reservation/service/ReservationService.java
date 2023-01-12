package com.omc.domain.reservation.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ProductService productService;
    private final CancelService cancelService;
    private final ProductRepository productRepository;

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

        Reservation reservation = Reservation.builder()
                .member(member)
                //회원정보, 결제상태 추가해야함
                .product(product)
//                .seller(product.getSeller())
                .checkIn(LocalDateTime.of(LocalDate.parse(request.getStartDate()), LocalTime.parse(product.getCheckIn())))
                .checkOut(LocalDateTime.of(LocalDate.parse(request.getEndDate()), LocalTime.parse(product.getCheckOut())))
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

    public List<ReservationDto.Response> getReservationDtoList() {
        List<Reservation> reservationList = reservationRepository.findAllByOrderByIdDesc();

        List<ReservationDto.Response> responseList = reservationList.stream().map(reservation -> toResponseDto(reservation)).collect(Collectors.toList());

        return responseList;
    }

    private ReservationDto.Response toResponseDto(Reservation reservation) {
        ReservationDto.Response responseDto = ReservationDto.Response.builder()
                .reservationId(reservation.getUniqueId())
                .phoneNumber(reservation.getPhoneNumber())
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
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
    public void cancelReservation(long reservationId, CancelDto.Request request) {
        Reservation reservation = findById(reservationId);
        if (reservation == null) {
            throw new BusinessException(ErrorCode.RESERVATION_NOT_FOUND);
        }
        // 취소 사유 생성
        reservation.setIsCancelOn(cancelService.createCancel(request, reservation));
        reservationRepository.save(reservation);
    }
}
