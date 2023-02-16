package com.omc.domain.member.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SingleParamDto {
    @NotNull(message = "입력해주세요")
    String param;
}
