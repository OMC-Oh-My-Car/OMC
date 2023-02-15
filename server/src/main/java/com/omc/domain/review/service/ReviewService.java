package com.omc.domain.review.service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.omc.domain.member.entity.Member;
import com.omc.global.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omc.domain.reservation.entity.Reservation;
import com.omc.domain.reservation.service.ReservationService;
import com.omc.domain.review.dto.ReviewDto;
import com.omc.domain.review.entity.Review;
import com.omc.domain.review.repository.ReviewRepository;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReservationService reservationService;
    private final Util util;

    DecimalFormat decimalFormat = new DecimalFormat("0.0"); // 소수 1자리 변환

    @Transactional
    public void createReview(ReviewDto.Request request, long reservationId, Member member) {
        Reservation reservation = reservationService.findById(reservationId);

        if (reservation.getIsCancel() == 1) {
            throw new BusinessException(ErrorCode.CANCEL_CANT_WRITE);
        }

        // 체크인 이후부터 작성 가능
        if (LocalDateTime.now().isBefore(reservation.getCheckIn())) {
            throw new BusinessException(ErrorCode.NOT_YET_CHECKIN);
        }

        // 해당 예약에 이미 리뷰가 있는지 중복 확인
        if (reservation.getReview() != null) {
            throw new BusinessException(ErrorCode.REVIEW_ALREADY_EXIST);
        }

        Review review = Review.builder()
                .reservation(reservation)
                .member(member)
//                .product(reservation.getProduct())
                .content(request.getContent())
                .totalStar(toStarPoint(request.getTotalStar()))
                .starCleanliness(toStarPoint(request.getStarCleanliness()))
                .starAccuracy(toStarPoint(request.getStarAccuracy()))
                .starLocation(toStarPoint(request.getStarLocation()))
                .starCostEffective(toStarPoint(request.getStarCostEffective()))
                .build();

        reservation.setReview(review);

//        reviewRepository.save(review);
    }

    private Double toStarPoint(Double num) {
        return Double.parseDouble(decimalFormat.format(num));
    }

    @Transactional
    public void modifyReview(ReviewDto.Request request, long reviewId, Member member) {
        // 리뷰 있는지 조회
        Review review = findById(reviewId);

        if (review == null) {
            throw new BusinessException(ErrorCode.REVIEW_NOT_FOUND);
        }

        // 작성자 동일한지 조회
        if (review.getMember().getId() != member.getId()) {
            throw new BusinessException(ErrorCode.NO_PERMISSION);
        }

        review.modify(request.getContent(), request.getTotalStar(), request.getStarCleanliness(), request.getStarAccuracy(),
                request.getStarLocation(), request.getStarCostEffective());

        reviewRepository.save(review);
    }

    private Review findById(long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public ReviewDto.Response findByResId(long reservationId) {
        Review review = reviewRepository.findByReservationId(reservationId).orElse(null);
        if (review == null) {
            throw new BusinessException(ErrorCode.REVIEW_NOT_FOUND);
        }
        return toResponseDto(review);
    }

    @Transactional
    public void deleteReview(long reviewId, Member member) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            throw new BusinessException(ErrorCode.REVIEW_NOT_FOUND);
        }

        // 작성자 동일한지 조회
        if (review.getMember().getId() != member.getId()) {
            throw new BusinessException(ErrorCode.NO_PERMISSION);
        }

        review.getReservation().deleteReview();
        reviewRepository.delete(review);
    }

    public Page<Review> getProductReviews(long productId, ReviewDto.Search search) {
        Pageable pageable = PageRequest.of(Math.toIntExact(search.getPage() - 1),
                Math.toIntExact(search.getSize()),
                Sort.by("id").descending());
//        Page<Review> reviewPage = reviewRepository.findAllByProductId(productId, pageable);
        Page<Review> reviewPage = reviewRepository.findAllByReservationProductId(productId, pageable);

        return reviewPage;
    }

    public ReviewDto.productTotalStar getProductAvg(long productId) {
        Object[] avgList = reviewRepository.findReviewsAvgByProductId(productId);
        Object[] valueArr = (Object[]) avgList[0];

//        return ReviewDto.productTotalStar.builder()
//                .totalStar(Double.valueOf(avgList[0].toString()))
//                .starCleanliness(Double.valueOf(avgList[1].toString()))
//                .starAccuracy(Double.valueOf(avgList[2].toString()))
//                .starLocation(Double.valueOf(avgList[3].toString()))
//                .starCostEffective(Double.valueOf(avgList[4].toString()))
//                .build();

        return ReviewDto.productTotalStar.builder()
                .totalStar(Double.valueOf(decimalFormat.format(valueArr[0])))
                .starCleanliness(Double.valueOf(decimalFormat.format(valueArr[1])))
                .starAccuracy(Double.valueOf(decimalFormat.format(valueArr[2])))
                .starLocation(Double.valueOf(decimalFormat.format(valueArr[3])))
                .starCostEffective(Double.valueOf(decimalFormat.format(valueArr[4])))
                .build();
    }

    public List<ReviewDto.Response> pageToResponseList(List<Review> content) {
        return content.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    private ReviewDto.Response toResponseDto(Review review) {

        return ReviewDto.Response.builder()
                .content(review.getContent())
                .totalStar(review.getTotalStar())
                .starCleanliness(review.getStarCleanliness())
                .starAccuracy(review.getStarAccuracy())
                .starLocation(review.getStarLocation())
                .starCostEffective(review.getStarCostEffective())
                .createTime(util.convertReviewLocalDateTime(review.getCreatedAt()))
                .build();
    }
}
