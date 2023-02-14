package com.omc.domain.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class SingleParamDto {
    @NotNull(message = "입력해주세요")
    String param;
}
