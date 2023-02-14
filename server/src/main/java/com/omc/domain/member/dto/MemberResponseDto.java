package com.omc.domain.member.dto;

import com.omc.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private String username;
    private String email;
    private String nickname;
    private String phone;
    private String profileImg;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getUsername(), member.getEmail(), member.getNickname(), member.getPhone(), member.getProfileImg());
    }
}
