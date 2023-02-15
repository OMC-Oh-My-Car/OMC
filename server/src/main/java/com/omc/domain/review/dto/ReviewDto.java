package com.omc.domain.review.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewDto {

    @Getter
    @Builder
    public static class Request {
        @NotBlank
        private String content;
        @PositiveOrZero
        @Max(value = 5)
        private Double totalStar; // 총 평점, 소수 1자리까지
        @PositiveOrZero
        @Max(value = 5)
        private Double starCleanliness; // 청결도, 소수 1자리까지
        @PositiveOrZero
        @Max(value = 5)
        private Double starAccuracy; // 정확도, 소수 1자리까지
        @PositiveOrZero
        @Max(value = 5)
        private Double starLocation; // 위치, 소수 1자리까지
        @PositiveOrZero
        @Max(value = 5)
        private Double starCostEffective; // 가격 대비 만족도, 소수 1자리까지
    }

    @Getter
    @Builder
    public static class Response {
        private String content;
        private Double totalStar; // 총 평점, 소수 1자리까지
        private Double starCleanliness; // 청결도, 소수 1자리까지
        private Double starAccuracy; // 정확도, 소수 1자리까지
        private Double starLocation; // 위치, 소수 1자리까지
        private Double starCostEffective; // 가격 대비 만족도, 소수 1자리까지
        private String createTime;
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

    @Getter
    @Builder
    public static class productTotalStar {
        private Double totalStar; // 총 평점, 소수 1자리까지
        private Double starCleanliness; // 청결도, 소수 1자리까지
        private Double starAccuracy; // 정확도, 소수 1자리까지
        private Double starLocation; // 위치, 소수 1자리까지
        private Double starCostEffective; // 가격 대비 만족도, 소수 1자리까지
    }
}
