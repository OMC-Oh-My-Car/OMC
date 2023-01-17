package com.omc.domain.review.entity;

import com.omc.domain.product.entity.Product;
import com.omc.domain.reservation.entity.Reservation;
import com.omc.global.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
public class Review extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "review")
    private Reservation reservation;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

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
    public Review(Reservation reservation, Product product, String content, Double totalStar,
                  Double starCleanliness, Double starAccuracy, Double starLocation, Double starCostEffective) {
        this.reservation = reservation;
        this.product = product;
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
