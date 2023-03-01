package com.omc.domain.payment.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;

public class PaymentDto {

    @Getter
    @Builder
    public static class Request{
        String paymentKey;
        String orderId;
        long amount;

        @NotBlank
        private long productId;
        @NotBlank
        private String phoneNumber;
        @NotBlank
        private String startDate; // 입실 날짜
        @NotBlank
        private String endDate; // 퇴실 날짜
    }
}
