package com.omc.domain.reservation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationSimpleDto {
    private String checkIn;
    private String checkOut;
}
