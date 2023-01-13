package com.omc.domain.review.repository;

import com.omc.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReservationId(long reservationId);

    @Query(value = "SELECT re.* from review re, reservation rs ,product pr"
            + " where re.reservation_id = rs.id "
            + " and rs.product_id = pr.id "
            + " and product.id=?1 ",
            nativeQuery = true)
    Page<Review> findAllByproductId(long productId, Pageable pageable);
}
