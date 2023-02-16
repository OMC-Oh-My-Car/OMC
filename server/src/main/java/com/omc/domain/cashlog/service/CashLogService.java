package com.omc.domain.cashlog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omc.domain.cashlog.entity.CashLog;
import com.omc.domain.cashlog.entity.EventType;
import com.omc.domain.cashlog.repository.CashLogRepository;
import com.omc.domain.member.entity.Member;
import com.omc.domain.reservation.entity.Reservation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CashLogService {
    private final CashLogRepository cashLogRepository;

    @Transactional
    public void addCashLog(Member member, long price, EventType eventType, Reservation reservation) {
        CashLog cashLog = CashLog.builder()
                .member(member)
                .price(price)
                .eventType(eventType)
                .reservation(reservation)
                .build();

        cashLogRepository.save(cashLog);
    }

}
