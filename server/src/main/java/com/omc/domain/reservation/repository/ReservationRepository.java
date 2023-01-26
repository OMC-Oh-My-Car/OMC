package com.omc.domain.reservation.repository;

import com.omc.domain.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAllByOrderByIdDesc(Pageable pageable);
    Page<Reservation> findAllByProductId(long productId, Pageable pageable);
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.product.id = :productId AND r.checkIn <= :regCheckIn AND r.checkOut >= :regCheckIn")
    Long countByCheckInBetween(@Param("productId") long productId, @Param("regCheckIn") LocalDateTime regCheckIn);
}
