package com.omc.domain.img.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.omc.domain.member.entity.Member;
import com.omc.global.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class MemberImg extends BaseEntity {
    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String imgName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @Builder
    public MemberImg(String imgUrl, Member member, String imgName) {
        this.imgUrl = imgUrl;
        this.member = member;
        this.imgName = imgName;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
