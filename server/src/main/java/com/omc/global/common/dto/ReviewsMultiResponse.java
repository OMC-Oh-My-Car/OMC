package com.omc.global.common.dto;

import com.omc.domain.review.dto.ReviewDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ReviewsMultiResponse<T> extends MultiResponse{
    public ReviewsMultiResponse(List data, Page page) {
        super(data, page);
    }
    private ReviewDto.productTotalStar productTotalStar;

    public ReviewsMultiResponse(ReviewDto.productTotalStar productTotalStar, List data, Page page) {
        super(data, page);
        this.productTotalStar = productTotalStar;
    }
}
