package com.omc.domain.reservation.repository;

import com.omc.domain.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAllByOrderByIdDesc(Pageable pageable);
    Page<Reservation> findAllByProductIdOrderByIdDesc(long productId, Pageable pageable);
}
