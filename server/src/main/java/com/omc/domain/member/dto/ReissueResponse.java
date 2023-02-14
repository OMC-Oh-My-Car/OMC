package com.omc.domain.member.dto;

import com.omc.domain.member.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReissueResponse {

    private String email;
    private String username;
    private String nickname;
    private String userRole;

    @Builder
    public ReissueResponse(String email, String username, String nickname, String userRole) {
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.userRole = userRole;
    }

    public static ReissueResponse toResponse(Member member) {
        return ReissueResponse.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .nickname(member.getNickname())
                .userRole((member.getUserRole()).toString())
                .build();
    }

}
