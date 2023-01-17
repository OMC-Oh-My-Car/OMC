package com.omc.domain.cancel.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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
