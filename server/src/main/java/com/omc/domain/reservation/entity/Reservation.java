package com.omc.domain.reservation.entity;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.omc.domain.cancel.entity.Cancel;
import com.omc.domain.member.entity.Member;
import com.omc.domain.product.entity.Product;
import com.omc.domain.review.entity.Review;
import com.omc.global.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Reservation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    private String uniqueId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    private String phoneNumber;
    private LocalDateTime checkIn; // 체크인
    private LocalDateTime checkOut; // 체크아웃

    //    private LocalDate startDate; // 입실 날짜
//    private LocalDate endDate; // 퇴실 날짜
    private String status; // 결제상태
    private int isCancel; // 취소 여부
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Review review;
    @OneToOne
    @JoinColumn(name = "cancel_id")
    private Cancel cancel;

    @Enumerated
    @Column(name = "reservation_status")
    private ReservationStatus reservationStatus;

    @Builder
    public Reservation(Product product, Member member,
                       String phoneNumber, LocalDateTime checkIn, LocalDateTime checkOut,
//                       LocalDate startDate, LocalDate endDate,
                       String status, int isCancel) {
        this.product = product;
        this.member = member;
        this.uniqueId = makeUniqueId();
        this.phoneNumber = phoneNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
//        this.startDate = startDate;
//        this.endDate = endDate;
        this.status = status;
        this.isCancel = isCancel;
        this.reservationStatus = ReservationStatus.RESERVATION_COMPLETE;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void deleteReview() {
        this.review = null;
    }

    public void setIsCancelOn(Cancel cancel) {
        this.isCancel = 1;
        this.cancel = cancel;
    }

    public String makeUniqueId() {
        DecimalFormat decimalFormat1 = new DecimalFormat("0000");
        DecimalFormat decimalFormat2 = new DecimalFormat("00");

        String uniqueId = LocalDate.now().getYear() + "-" +
                decimalFormat2.format(LocalDate.now().getMonthValue()) +
                decimalFormat2.format(LocalDate.now().getDayOfMonth()) + "-" +
                decimalFormat1.format(Math.random() * 10000);

        return uniqueId;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

}
