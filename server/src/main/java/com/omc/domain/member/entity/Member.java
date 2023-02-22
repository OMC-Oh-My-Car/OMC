package com.omc.domain.member.entity;

import java.util.Map;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.omc.domain.member.dto.MemberModifyDto;
import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.global.common.BaseEntity;
import com.omc.global.util.Util;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Social isSocial;

    @Column(nullable = false, unique = true)
    private String phone;

    private String profileImg;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public void setPassword(String password) {
        this.password = password;
    }

    public MemberResponseDto toResponseDto() {
        return MemberResponseDto.builder()
                .username(username)
                .email(email)
                .nickname(nickname)
                .phone(phone)
                .profileImg(profileImg)
                .build();
    }

    public Map<String, Object> getAccessTokenClaims() {
        return Util.mapOf(
                "username", getUsername(),
                "nickname", getNickname(),
                "email", getEmail(),
                "userRole", getUserRole()
        );
    }

    public void patch(MemberModifyDto memberModifyDto) {
        Optional.ofNullable(memberModifyDto.getUsername())
                .ifPresent(username -> this.username = username);
        Optional.ofNullable(memberModifyDto.getEmail())
                .ifPresent(email -> this.email = email);
        Optional.ofNullable(memberModifyDto.getProfileImg())
                .ifPresent(image -> this.profileImg = image);
        Optional.ofNullable(memberModifyDto.getPhone())
                .ifPresent(phone -> this.phone = phone);
    }
}
