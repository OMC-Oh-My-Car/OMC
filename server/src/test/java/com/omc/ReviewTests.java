package com.omc;

import com.omc.domain.member.entity.Member;
import com.omc.domain.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;

@SpringBootTest
public class ReviewTests {


    @Test
    void createReview() {
        Member member = Member.builder()
                .username("test")
                .email("test1@test.com")
                .nickname("testuser")
                .phone("010-1234-5678")
                .build();

        Product product = Product.builder()
                .subject("testProduct")
                .description("테스트입니다.")
                .address("수원시 권선구")
                .zipcode("12345")
                .reportCount(0L)
                .telephone("02-123-4567")
                .count(0L)
                .price(50000L)
                .star(4.5)
                .likes(10L)
                .isStop(0L)
                .member(member)
                .checkIn("2023.01.11")
                .checkOut("2023.01.15")
                .build();


    }

}
