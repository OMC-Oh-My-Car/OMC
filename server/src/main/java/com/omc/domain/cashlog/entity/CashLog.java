package com.omc.domain.cashlog.entity;

import javax.persistence.*;

import com.omc.domain.member.entity.Member;
import com.omc.domain.reservation.entity.Reservation;
import com.omc.global.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CashLog extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    private Long price;
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToOne
    private Reservation reservation;

    @Builder
    public CashLog(Member member, long price, EventType eventType, Reservation reservation) {
        this.member = member;
        this.price = price;
        this.eventType = eventType;
        this.reservation = reservation;
    }
}
