package com.omc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;

@SpringBootTest
public class ReviewTests {

    @Test
    void createReview() {
        DecimalFormat df = new DecimalFormat("0.0");
        Double a = 4.567;
        System.out.println(df.format(a).getClass());
    }

}
