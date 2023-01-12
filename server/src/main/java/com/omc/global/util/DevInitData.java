package com.omc.global.util;

import com.omc.domain.product.service.ProductService;
import com.omc.domain.reservation.service.ReservationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevInitData {
    @Bean
    CommandLineRunner initData(ProductService productService, ReservationService reservationService) {
        return args -> {

        };
    }
}
