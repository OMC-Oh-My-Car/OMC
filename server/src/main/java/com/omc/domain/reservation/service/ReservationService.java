package com.omc.domain.reservation.service;

import com.omc.domain.product.entity.Product;
import com.omc.domain.product.service.ProductService;
import com.omc.domain.reservation.dto.ReservationDto;
import com.omc.domain.reservation.entity.Reservation;
import com.omc.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ProductService productService;

    public Reservation findById(long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        return reservation;
    }

    public void createReservation(ReservationDto.Request request) {
        Product product = productService.findById(request.getProductId());

        if (product == null) {
            // 상품 존재하지 않아서 예외처리
        }

        Reservation reservation = Reservation.builder()
                //                .member()
                //회원정보, 결제상태 추가해야함
                .product(product)
//                .seller(product.getSeller())
//                .checkIn(LocalDateTime.of(request.getStartDate(), product.getCheckIn()))
//                .checkIn(LocalDateTime.of(request.getEndDate(), product.getCheckOut()))
                .phoneNumber(request.getPhoneNumber())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCancel(0)
                .build();

        reservationRepository.save(reservation);
    }

    public ReservationDto.Response getResponseDto(long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            // 예약 존재하지 않음 예외처리

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
                .reservationId(reservation.getId())
                .phoneNumber(reservation.getPhoneNumber())
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
                .isCancel(reservation.getIsCancel())
                .build();

        return responseDto;
    }
}
