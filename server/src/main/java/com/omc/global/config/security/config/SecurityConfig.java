package com.omc.global.config.security.config;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

import com.omc.domain.member.repository.MemberRepository;
import com.omc.domain.member.repository.RefreshTokenRepository;
import com.omc.global.jwt.filter.JwtAuthenticationFilter;
import com.omc.global.jwt.filter.JwtVerificationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.omc.global.config.security.handler.OAuth2AuthenticationSuccessHandler;
import com.omc.global.config.security.service.OAuth2UserService;
import com.omc.global.jwt.JwtAccessDeniedHandler;
import com.omc.global.jwt.JwtAuthenticationEntryPoint;
import com.omc.global.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final OAuth2UserService oAuth2UserService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .headers().frameOptions().sameOrigin()
//                .and()
                .csrf(
                        csrf -> csrf.disable()
                )
                .cors()
                .and()// 타 도메인 호출 가능
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .httpBasic().disable() // httpBasic 로그인 방식 끄기
                .formLogin().disable() // 폼 로그인 방식 끄기
                .apply(new CustomFilterConfigurer())
                .and()
                .sessionManagement( // 세션 사용 안함
                        sessionManagement ->
                        sessionManagement.sessionCreationPolicy(STATELESS)
                )
//                .addFilter(new JwtAuthenticationFilter(tokenProvider, authenticationManager))
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests
                                .antMatchers("/member/login", "/member", "/member/confirm/mail", "/member/find/id", "/seller")
                                .permitAll()
                                .anyRequest()
                                .authenticated() // 최소자격 : 로그인
                )
                .oauth2Login(
                        oauth2Login -> oauth2Login
                                .defaultSuccessUrl("/member/detail")
                                .successHandler(oAuth2AuthenticationSuccessHandler)
                                .userInfoEndpoint()
                                .userService(oAuth2UserService)
                )


//                .and()
        ; // 세션 사용안함
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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin(FRONT_LOCAL);
//        configuration.addAllowedOrigin(FRONT_REMOTE);
//        configuration.addAllowedOrigin(FRONT_REMOTE_HTTPS);
//        configuration.addAllowedOrigin(DOMAIN);
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PUT"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("Set-Cookie");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(tokenProvider, authenticationManager, refreshTokenRepository);
            jwtAuthenticationFilter.setFilterProcessesUrl("/member/login");
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(tokenProvider, memberRepository);

            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
