package com.omc.domain.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberModifyDto {
    @Email
    private String email;
    private String username;

    private String profileImg;
    private String phone;
}
