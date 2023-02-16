package com.omc.domain.review.api;

import java.util.List;

import com.omc.domain.member.entity.Member;
import com.omc.global.common.annotation.CurrentMember;
import com.omc.global.common.dto.ReviewsMultiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omc.domain.review.dto.ReviewDto;
import com.omc.domain.review.entity.Review;
import com.omc.domain.review.service.ReviewService;
import com.omc.global.common.dto.MultiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    // @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/{reservationId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createReview(@RequestBody ReviewDto.Request request, @PathVariable long reservationId,
                                          @CurrentMember Member member) {
        reviewService.createReview(request, reservationId, member);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/{reviewId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> modifyReview(@RequestBody ReviewDto.Request request, @PathVariable long reviewId,
                                          @CurrentMember Member member) {
        reviewService.modifyReview(request, reviewId, member);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable long reviewId, @CurrentMember Member member) {
        reviewService.deleteReview(reviewId, member);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<?> getReviews(@PathVariable long productId,
                                        @ModelAttribute ReviewDto.Search search) {
        ReviewDto.productTotalStar productTotalStar = reviewService.getProductAvg(productId);

        Page<Review> reviewPage = null;
        List<ReviewDto.Response> responseList = null;

        if (productTotalStar != null) {
            reviewPage = reviewService.getProductReviews(productId, search);
            responseList = reviewService.pageToResponseList(reviewPage.getContent());
        } else {
            reviewPage = Page.empty();
        }

        return new ResponseEntity<>(new ReviewsMultiResponse<>(productTotalStar, responseList, reviewPage), HttpStatus.OK);
    }
}
