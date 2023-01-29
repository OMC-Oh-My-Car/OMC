package com.omc.global.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omc.domain.member.entity.RefreshToken;
import com.omc.domain.member.repository.RefreshTokenRepository;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omc.domain.member.dto.LoginDto;
import com.omc.domain.member.dto.TokenDto;
import com.omc.domain.member.entity.AuthMember;
import com.omc.global.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        log.debug("start AuthenticationFilter");
        ObjectMapper om = new ObjectMapper();
//        SignUpRequestDto signUpRequest = om.readValue(request.getInputStream(), SignUpRequestDto.class);
        LoginDto loginDto = om.readValue(request.getInputStream(), LoginDto.class);
        log.debug("Email : " + loginDto.getEmail() + ", Password : " + loginDto.getPassword());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        log.info("JwtAuthenticationFilter : authenticationToken 생성완료");

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        AuthMember customUserDetails = (AuthMember) authentication.getPrincipal();
        log.debug("Authentication : " + customUserDetails.getMember().getEmail());

        if (authentication.isAuthenticated()) {
            log.debug("success");
        } else {
            log.debug("failed");
        }


        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        log.debug("response : " + response.getHeaderNames());
        log.debug("successfulAuthentication 실행");
        AuthMember authMember = (AuthMember) authResult.getPrincipal();
        log.debug("authMember : " + authMember.getUsername());

        TokenDto tokenDto = tokenProvider.generateTokenWithAuthentication(authResult);
        log.debug("AccessToken : " + tokenDto.getAccessToken());
        log.debug("RefreshToken : " + tokenDto.getRefreshToken());
        String refreshToken = tokenDto.getRefreshToken();

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .key(authMember.getUsername())
                .value(refreshToken)
                .build();

        refreshTokenRepository.save(refreshTokenEntity);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());
        log.debug("RefreshToken Header 설정");

        response.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        log.debug("AccessToken Header 설정");

        log.debug("Authorization : " + response.getHeader("Authorization"));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
                "{" +
                        "\"email\":\"" + authMember.getMember().getEmail() + "\","
                        +  "\"username\":\"" + authMember.getMember().getUsername() + "\","
                        + "\"nickname\":\"" + authMember.getMember().getNickname() + "\"" +
                        "}"
        );
        log.debug("getWriter 실행");

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }
}
