package com.omc.domain.reservation.dto;

import com.omc.domain.member.entity.Member;
import com.omc.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationDto {

    @Getter
    @Builder
    public static class Request {
        private long productId;
        @NotBlank
        private String phoneNumber;
        @NotBlank
        private String startDate; // 입실 날짜
        @NotBlank
        private String endDate; // 퇴실 날짜
//        private LocalDate startDate; // 입실 날짜
//        private LocalDate endDate; // 퇴실 날짜
    }

    @Getter
    @Builder
    public static class Response {
        private String reservationId;
        private String phoneNumber;
        private LocalDateTime checkIn;
        private LocalDateTime checkOut;
        private int isCancel;
    }
}
