package com.omc.domain.reservation.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReservationDto {

    @Getter
    @Builder
    public static class Request {
        @NotBlank
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
        private long reservationId;
        private String title;
        private String thumbNail;
        private String reservationCode;
        private String phoneNumber;
        private String checkIn;
        private String checkOut;
        private int isCancel;
        private Boolean hasReview;
        private String status;
    }

    @Getter
    @Builder
    public static class DetailDto {
        private long reservationId;
        private String title;
        private String thumbNail;
        private String reservationCode;
        private String phoneNumber;
        private String checkIn;
        private String checkOut;
        private int isCancel;
        private String email;
        private String name;
        private Long productId;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Search {
        private Long page;
        private Long size;
        private String sort;

        public Search(@RequestParam(value = "page", required = false) Long page, @RequestParam(value = "sort", required = false) String sort){
//            if (page == null || page <= 0) {
//                this.page = 1L;
//            }
            this.page = page == null ? 1 : page;
            this.size = 18L;
            this.sort = sort == null ? "id" : sort;
        }
    }

}
