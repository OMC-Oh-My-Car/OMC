package com.omc.domain.reservation.service;

import com.omc.domain.reservation.entity.Reservation;
import com.omc.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation findById(long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        return reservation;
    }
}
