package com.omc.global.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(
                        csrf -> csrf.disable()
                )
                .cors(
                        cors -> cors.disable() // 타 도메인 호출 가능
                )
                .httpBasic().disable() // httpBaic 로그인 방식 끄기
                .formLogin().disable() // 폼 로그인 방식 끄기
                .sessionManagement(
                        sessionManagement ->
                        sessionManagement.sessionCreationPolicy(STATELESS)
                ); // 세션 사용안함
                // 없어도 된다.
//                .logout(logout -> logout
//                        .logoutUrl("/logout"))
                ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
