package com.omc.domain.member.dto;

import com.omc.domain.img.entity.MemberImg;
import com.omc.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private String username;
    private String email;
    private String nickname;
    private String phone;
    private List<String> profileImg;

    public static MemberResponseDto of(Member member) {
        List<String> memberImgUrls = new ArrayList<>();
        for (MemberImg memberImg : member.getMemberImgList()) {
            memberImgUrls.add(memberImg.getImgUrl());
        }

        return new MemberResponseDto(member.getUsername(), member.getEmail(), member.getNickname(), member.getPhone(), memberImgUrls);
    }
}
