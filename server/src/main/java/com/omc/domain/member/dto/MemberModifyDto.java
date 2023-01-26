package com.omc.domain.member.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
