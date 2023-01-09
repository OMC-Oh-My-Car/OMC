package com.omc.domain.reservation.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.omc.domain.member.entity.Member;
import com.omc.domain.product.entity.Product;
import com.omc.global.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Reservation extends BaseEntity {

    @ManyToOne
    private Product product;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Member member;

//    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
//    private Seller seller;

    @NotBlank
    private String phoneNumber;

    private LocalDateTime checkIn; // 체크인
    private LocalDateTime checkOut; // 체크아웃

    private LocalDate startDate; // 입실 날짜
    private LocalDate endDate; // 퇴실 날짜

    private String status; // 결제상태

    private int isCancel; // 취소 여부

    @Builder
    public Reservation(Product product, Member member,
//                       Seller seller,
                       String phoneNumber, LocalDateTime checkIn, LocalDateTime checkOut,
                       LocalDate startDate, LocalDate endDate, String status, int isCancel) {
        this.product = product;
        this.member = member;
//        this.seller = seller;
        this.phoneNumber = phoneNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.isCancel = isCancel;
    }
}
