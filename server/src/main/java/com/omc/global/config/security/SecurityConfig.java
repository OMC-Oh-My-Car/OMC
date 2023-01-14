package com.omc.global.config.security;

import com.omc.domain.member.repository.MemberRepository;
import com.omc.domain.member.repository.RefreshTokenRepository;
import com.omc.global.jwt.JwtSecurityConfig;
import com.omc.global.jwt.filter.JwtFilter;
import com.omc.global.config.security.handler.MemberAuthenticationSuccessHandler;
import com.omc.global.jwt.JwtAccessDeniedHandler;
import com.omc.global.jwt.JwtAuthenticationEntryPoint;
import com.omc.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
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

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .csrf(
                        csrf -> csrf.disable()
                )
                .cors(
                        cors -> cors.disable() // 타 도메인 호출 가능
                )
                .httpBasic().disable() // httpBasic 로그인 방식 끄기
                .formLogin().disable() // 폼 로그인 방식 끄기
                .sessionManagement( // 세션 사용 안함
                        sessionManagement ->
                        sessionManagement.sessionCreationPolicy(STATELESS)
                )
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests
                                .antMatchers("/member/login", "/member", "/member/confirm/mail", "/member/find/id", "/seller")
                                .permitAll()
                                .anyRequest()
                                .authenticated() // 최소자격 : 로그인
                )
                .apply(new JwtSecurityConfig(tokenProvider))
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

//    private class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//
//            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(tokenProvider, authenticationManager, memberRepository);
//            jwtAuthenticationFilter.setFilterProcessesUrl("/member/login");
//            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler(refreshTokenRepository));
//
////            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(tokenProvider, memberService);
//            JwtFilter jwtFilter = new JwtFilter(tokenProvider);
//            http
//                    .addFilter(jwtAuthenticationFilter)
//                    .addFilterAfter(jwtFilter, JwtAuthenticationFilter.class);
//        }
//    }
}
