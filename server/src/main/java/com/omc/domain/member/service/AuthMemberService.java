package com.omc.domain.member.service;

import com.omc.domain.member.dto.*;
import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.entity.RefreshToken;
import com.omc.domain.member.exception.*;
import com.omc.domain.member.repository.MemberRepository;
import com.omc.domain.member.repository.RefreshTokenRepository;
import com.omc.global.jwt.TokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthMemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public MemberResponseDto join(SignUpRequestDto signUpRequestDto) {
        if (memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new DuplicateEmail();
        }

        if (memberRepository.existsByUsername(signUpRequestDto.getUsername())) {
            throw new DuplicateUsername();
        }

        if (memberRepository.existsByNickname(signUpRequestDto.getNickname())) {
            throw new DuplicateNickname();
        }

        // encoding된 password를 사용한 build
        Member member = signUpRequestDto.encodePasswordSignUp(passwordEncoder);

        // 객체형태의 Response Body 생성
        return memberRepository.save(member).toResponseDto();
    }

    public TokenDto login(LoginDto loginDto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenWithAuthentication(authentication);

        // TokenDto의 accessToken을 Header의 Authorization이름으로 넣어줌
        response.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());

        RefreshToken refreshToken = RefreshToken.builder()
                .key(loginDto.getEmail())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 토큰 발급
        return tokenDto;
    }

    public ReissueResponse reissue(String accessToken, HttpServletResponse response) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        Claims claims = tokenProvider.getClaims(accessToken);
//        log.debug("email : " + authentication.getName());

        RefreshToken refreshToken = refreshTokenRepository.findByKey((String)claims.get("email"))
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        String savedRefreshToken = refreshToken.getValue();
        log.debug("refreshToken : " + savedRefreshToken);

        if (!tokenProvider.validateToken(savedRefreshToken)) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        Member member = memberRepository.findByEmail(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        TokenDto tokenDto = tokenProvider.generateTokenWithAuthentication(authentication);
        String newRT = tokenDto.getRefreshToken();
        String newAT = tokenDto.getAccessToken();

//        if (!savedRefreshToken.getValue().equals(refreshToken)) {
//            throw new TokenInvalid();
//        }

        RefreshToken newRefreshToken = refreshToken.updateValue(newRT);
        refreshTokenRepository.save(newRefreshToken);

//        ResponseCookie cookie = ResponseCookie.from("refreshToken", newRT)
//                .maxAge(7 * 24 * 60 * 60)
//                .path("/")
//                .secure(true)
//                .sameSite("None")
//                .httpOnly(true)
//                .build();
//        response.setHeader("Set-Cookie", cookie.toString());

        response.setHeader("Authentication", "Bearer " + newAT);

        return ReissueResponse.toResponse(member);
    }


}
