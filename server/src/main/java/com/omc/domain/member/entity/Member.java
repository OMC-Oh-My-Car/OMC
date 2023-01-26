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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
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
        this.username = memberModifyDto.getUsername();
        this.email = memberModifyDto.getEmail();
        Optional.ofNullable(memberModifyDto.getProfileImg())
                .ifPresent(image -> this.profileImg = image);
        this.phone = memberModifyDto.getPhone();
    }
}
