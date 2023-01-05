package com.omc.domain.review.service;

import com.omc.domain.reservation.entity.Reservation;
import com.omc.domain.reservation.service.ReservationService;
import com.omc.domain.review.dto.ReviewDto;
import com.omc.domain.review.entity.Review;
import com.omc.domain.review.repository.ReviewRepository;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReservationService reservationService;

    DecimalFormat decimalFormat = new DecimalFormat("0.0"); // 소수 1자리 변환

    @Transactional
    public void createReview(ReviewDto.Request request, long reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        // 해당 예약에 이미 리뷰가 있는지 중복 확인
//        if () {
//            throw new BusinessException(ErrorCode.ALREADY_EXIST);
//        }

        Review review = Review.builder()
                .reservation(reservation)
                .content(request.getContent())
                .totalStar(toStarPoint(request.getTotalStar()))
                .starCleanliness(toStarPoint(request.getStarCleanliness()))
                .starAccuracy(toStarPoint(request.getStarAccuracy()))
                .starLocation(toStarPoint(request.getStarLocation()))
                .starCostEffective(toStarPoint(request.getStarCostEffective()))
                .build();

        reviewRepository.save(review);
    }

    private Double toStarPoint(Double num) {
        return Double.parseDouble(decimalFormat.format(num));
    }

    @Transactional
    public void modifyReview(ReviewDto.Request request, long reviewId) {
        // 리뷰 있는지 조회
        // 요청자와 작성자 동일한지 확인 필요

        Review review = findById(reviewId);

        if (review == null) {
            throw new BusinessException(ErrorCode.REVIEW_NOT_FOUND);
        }

        review.of(request.getContent(), request.getTotalStar(), request.getStarCleanliness(), request.getStarAccuracy(),
                request.getStarLocation(), request.getStarCostEffective());

        reviewRepository.save(review);
    }

    private Review findById(long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public Review findByResId(long reservationId) {
        return reviewRepository.findByReservationId(reservationId).orElse(null);
    }

    public void findAllReviewsByDesc(long productId) {
        // 리뷰들의 예약 정보 중에서 productId가 일치하는 것을 찾아서 내림차순 정렬
    }

    @Transactional
    public void deleteReview(long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            throw new BusinessException(ErrorCode.REVIEW_NOT_FOUND);
        }
        reviewRepository.delete(review);
    }
}
