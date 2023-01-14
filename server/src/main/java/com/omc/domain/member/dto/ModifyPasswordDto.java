package com.omc.domain.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class ModifyPasswordDto {
    @NotBlank(message = "이전 비밀번호를 입력하세요.")
    private String oldPassword;

    @NotBlank(message = "새로운 비밀번호를 입력하세요.")
    private String newPassword;
}
