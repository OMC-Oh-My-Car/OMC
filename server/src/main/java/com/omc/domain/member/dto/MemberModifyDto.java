package com.omc.domain.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class MemberModifyDto {
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "성명을 입력해주세요.")
    private String username;
    @NotBlank(message = "프로필 사진을 입력해주세요.")
    private String profileImg;
    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phone;
}
