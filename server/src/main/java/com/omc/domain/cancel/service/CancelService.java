package com.omc.domain.cancel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omc.domain.cancel.dto.CancelDto;
import com.omc.domain.cancel.entity.Cancel;
import com.omc.domain.cancel.repository.CancelRepository;
import com.omc.domain.member.entity.Member;
import com.omc.domain.reservation.entity.Reservation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CancelService {
    private final CancelRepository cancelRepository;

    public Cancel findByResId(long reservationId) {
        return cancelRepository.findByReservationId(reservationId).orElse(null);
    }

    @Transactional
    public Cancel createCancel(CancelDto.Request request, Reservation reservation, Member member) {
        Cancel cancel = Cancel.builder()
                .reservation(reservation)
                .reason(request.getCancelReason())
                .member(member)
                .build();

        cancelRepository.save(cancel);
        return cancel;
    }
}
