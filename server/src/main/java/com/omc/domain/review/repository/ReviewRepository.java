package com.omc.domain.review.repository;

import com.omc.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReservationId(long reservationId);

//    @EntityGraph(attributePaths = {"reservation"})
//    Page<Review> findAllByProductId(long productId, Pageable pageable);

    @Query("SELECT r FROM Review r JOIN r.reservation rv WHERE rv.product.id = :productId")
    @EntityGraph(attributePaths = {"reservation"})
    Page<Review> findAllByReservationProductId(long productId, Pageable pageable);
}
