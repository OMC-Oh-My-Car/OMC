package com.omc.domain.cancel.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;

public class CancelDto {
    @Getter
    @Builder
    public static class Request {
        @NotBlank
        private String cancelReason;
    }

    @Getter
    @Builder
    public static class Response {
        private LocalDateTime cancelTime;
        private String cancelReason;
    }
}
