package com.omc.domain.member.dto;

import com.omc.domain.member.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReissueResponse {

    private String email;
    private String username;
    private String imageUrl;

    @Builder
    public ReissueResponse(String email, String imageUrl, String username) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.username = username;
    }

    public static ReissueResponse toResponse(Member member) {
        return ReissueResponse.builder()
                .email(member.getEmail())
                .imageUrl(member.getProfileImg())
                .username(member.getUsername())
                .build();
    }

}
