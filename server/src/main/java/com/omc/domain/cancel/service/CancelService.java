package com.omc.domain.cancel.service;

import com.omc.domain.cancel.dto.CancelDto;
import com.omc.domain.cancel.entity.Cancel;
import com.omc.domain.cancel.repository.CancelRepository;
import com.omc.domain.member.entity.Member;
import com.omc.domain.product.entity.Product;
import com.omc.domain.reservation.dto.ReservationDto;
import com.omc.domain.reservation.entity.Reservation;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CancelService {
    private final CancelRepository cancelRepository;

    public Cancel findByResId(long reservationId) {
        return cancelRepository.findByReservationId(reservationId).orElse(null);
    }

    @Transactional
    public Cancel createCancel(CancelDto.Request request, Reservation reservation) {
        Cancel cancel = Cancel.builder()
                .reservation(reservation)
                .reason(request.getCancelReason())
                .build();

        cancelRepository.save(cancel);
        return cancel;
    }
}
