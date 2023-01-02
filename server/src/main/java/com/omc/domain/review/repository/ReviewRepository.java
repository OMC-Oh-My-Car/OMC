package com.omc.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
