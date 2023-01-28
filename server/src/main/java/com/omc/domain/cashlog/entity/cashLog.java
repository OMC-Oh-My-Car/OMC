package com.omc.domain.cashlog.entity;

import com.omc.domain.member.entity.Member;
import com.omc.global.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class cashLog extends BaseEntity {
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


}
