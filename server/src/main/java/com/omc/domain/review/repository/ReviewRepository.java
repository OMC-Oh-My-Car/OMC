package com.omc.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.review.entity.Review;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReservationId(long reservationId);
}
