package com.omc.domain.review.api;

import com.omc.domain.review.dto.ReviewDto;
import com.omc.domain.review.entity.Review;
import com.omc.domain.review.service.ReviewService;
import com.omc.global.common.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    // @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/{reservationId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createReview(@RequestBody ReviewDto.Request request, @PathVariable long reservationId) {
        reviewService.createReview(request, reservationId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/{reviewId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> modifyReview(@RequestBody ReviewDto.Request request, @PathVariable long reviewId) {
        reviewService.modifyReview(request, reviewId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<?> getReviews(@PathVariable long productId,
                                        @ModelAttribute ReviewDto.Search search) {
        Page<Review> reviewPage = reviewService.getProductReviews(productId, search);
        List<ReviewDto.Response> responseList = reviewService.pageToResponseList(reviewPage.getContent());

        return new ResponseEntity<>(new MultiResponse<>(responseList, reviewPage), HttpStatus.OK);
    }
}
