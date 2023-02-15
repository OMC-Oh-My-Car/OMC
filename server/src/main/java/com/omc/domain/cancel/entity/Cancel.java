package com.omc.domain.cancel.entity;

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
public class Cancel extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cancel", cascade = CascadeType.ALL)
    private Reservation reservation;
    @ManyToOne
    private Member member;

    private String reason;

    @Builder
    public Cancel(Reservation reservation, String reason, Member member) {
        this.reservation = reservation;
        this.reason = reason;
        this.member = member;
    }
}
