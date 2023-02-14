package com.omc.domain.cancel.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

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

    private String reason;

    @Builder
    public Cancel(Reservation reservation, String reason) {
        this.reservation = reservation;
        this.reason = reason;
    }
}
