package com.omc.domain.cancel.repository;

import com.omc.domain.cancel.entity.Cancel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CancelRepository extends JpaRepository<Cancel, Long> {
    Optional<Cancel> findByReservationId(long reservationId);
}
