package com.omc.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
