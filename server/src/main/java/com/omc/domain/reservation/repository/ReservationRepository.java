package com.omc.domain.reservation.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.omc.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.omc.domain.reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAllByOrderByIdDesc(Pageable pageable);
    Page<Reservation> findAllByMemberOrderByIdDesc(Pageable pageable, Member member);
    Page<Reservation> findAllByProductId(long productId, Pageable pageable);
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.product.id = :productId AND r.checkIn <= :regCheckIn AND r.checkOut >= :regCheckIn")
    Long countByCheckInBetween(@Param("productId") long productId, @Param("regCheckIn") LocalDateTime regCheckIn);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.product.id = :productId AND r.checkIn <= :resCheckOut AND r.checkOut >= :resCheckIn")
    Long countByCheckInBetween(@Param("productId") long productId, @Param("resCheckIn") LocalDateTime resCheckIn,
                                           @Param("resCheckOut") LocalDateTime resCheckOut);
    @Query("SELECT r FROM Reservation r WHERE r.product.id = :productId AND r.checkIn <= :resCheckOut AND r.checkOut >= :resCheckIn")
    List<Reservation> findByCheckInBetween(@Param("productId") long productId, @Param("resCheckIn") LocalDateTime resCheckIn,
                                           @Param("resCheckOut") LocalDateTime resCheckOut);
}
