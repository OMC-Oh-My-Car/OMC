package com.omc.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.reservation.entity.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByOrderByIdDesc();
}
