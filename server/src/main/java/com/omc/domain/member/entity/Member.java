package com.omc.domain.member.entity;

import javax.persistence.*;

import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.global.common.BaseEntity;
import com.omc.global.util.Util;
import lombok.*;

import java.util.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false, unique = true)
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
}
