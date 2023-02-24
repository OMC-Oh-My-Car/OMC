package com.omc.domain.member.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.*;

import com.omc.domain.img.entity.MemberImg;
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberImg> memberImgList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole userRole;



    public void setPassword(String password) {
        this.password = password;
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
        Optional.ofNullable(memberModifyDto.getPhone())
                .ifPresent(phone -> this.phone = phone);
    }

    public void setProductImgList(List<MemberImg> memberImgs) {
        this.memberImgList = memberImgs;
        for (MemberImg memberImg : memberImgs) {
            memberImg.setMember(this);
        }
    }
}
