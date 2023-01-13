package com.omc.domain.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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

    @Getter
    @NoArgsConstructor
    public static class PageRequest {
        private Long page;
        private Long size;
        private String sort;

        PageRequest(@RequestParam(value = "page") Long page, @RequestParam(value = "sort", required = false) String sort){
            if (page == null || page <= 0) {
                this.page = 1L;
            }
            this.size = 18L;
            this.sort = sort == null ? "id" : sort;
        }
    }


}
