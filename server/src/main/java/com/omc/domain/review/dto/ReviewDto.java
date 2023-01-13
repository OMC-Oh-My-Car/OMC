package com.omc.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

public class ReviewDto {

    @Getter
    @Builder
    public static class Request {
        private String content;
        private Double totalStar; // 총 평점, 소수 1자리까지
        private Double starCleanliness; // 청결도, 소수 1자리까지
        private Double starAccuracy; // 정확도, 소수 1자리까지
        private Double starLocation; // 위치, 소수 1자리까지
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
        private LocalDateTime createTime;
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
