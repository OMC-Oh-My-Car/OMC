package com.omc.domain.review.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.omc.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"reservation"})
    Optional<Review> findByReservationId(long reservationId);

//    @EntityGraph(attributePaths = {"reservation"})
//    Page<Review> findAllByProductId(long productId, Pageable pageable);

    @Query("SELECT r FROM Review r JOIN r.reservation rv WHERE rv.product.id = :productId")
    @EntityGraph(attributePaths = {"reservation"})
    Page<Review> findAllByReservationProductId(long productId, Pageable pageable);
    @EntityGraph(attributePaths = {"reservation"})
    Optional<Review> findById(long reviewId);

    @Query("SELECT AVG(r.totalStar), AVG(r.starCleanliness), AVG(r.starAccuracy), AVG(r.starLocation), AVG(r.starCostEffective) " +
            "FROM Review r JOIN r.reservation rv " +
            "WHERE rv.product.id = :productId")
    @EntityGraph(attributePaths = {"reservation"})
    Object[] findReviewsAvgByProductId(long productId);
}
