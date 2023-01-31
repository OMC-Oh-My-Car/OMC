package com.omc.domain.cancel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.cancel.entity.Cancel;

public interface CancelRepository extends JpaRepository<Cancel, Long> {
    @EntityGraph(attributePaths = {"reservation"})
    Optional<Cancel> findByReservationId(long reservationId);
}
