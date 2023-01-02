package com.omc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OmcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmcApplication.class, args);
	}

}
