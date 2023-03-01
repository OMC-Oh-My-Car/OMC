package com.omc.domain.payment.dto;

import lombok.Builder;
import lombok.Getter;

public class PaymentDto {

    @Getter
    @Builder
    public static class Request{
        String paymentKey;
        String orderId;
        long amount;
    }
}
