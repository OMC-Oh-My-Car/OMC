package com.omc.domain.review.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.omc.domain.reservation.entity.Reservation;
import com.omc.global.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review extends BaseEntity {

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
    private Reservation reservation;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank
    private String content;

    @NotNull
    private Double totalStar; // 총 평점, 소수 1자리까지

    @NotNull
    private Double starCleanliness; // 청결도, 소수 1자리까지

    @NotNull
    private Double starAccuracy; // 정확도, 소수 1자리까지

    @NotNull
    private Double starLocation; // 위치, 소수 1자리까지

    @NotNull
    private Double starCostEffective; // 가격 대비 만족도, 소수 1자리까지

    @Builder
    public Review(Reservation reservation, String content, Double totalStar,
                  Double starCleanliness, Double starAccuracy, Double starLocation, Double starCostEffective) {
        this.reservation = reservation;
        this.content = content;
        this.totalStar = totalStar;
        this.starCleanliness = starCleanliness;
        this.starAccuracy = starAccuracy;
        this.starLocation = starLocation;
        this.starCostEffective = starCostEffective;
    }

    public void of(String content, Double totalStar, Double starCleanliness,
                   Double starAccuracy, Double starLocation, Double starCostEffective) {
        this.content = content;
        this.totalStar = totalStar;
        this.starCleanliness = starCleanliness;
        this.starAccuracy = starAccuracy;
        this.starLocation = starLocation;
        this.starCostEffective = starCostEffective;
    }
}
