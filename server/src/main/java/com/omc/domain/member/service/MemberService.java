package com.omc.domain.member.service;

import com.omc.domain.member.dto.SignUpRequestDto;
import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.repository.MemberRepository;
import com.omc.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public MemberResponseDto join(SignUpRequestDto signUpRequestDto) {
        // 객체화된 Request Body의 value로 Member Entity를 Build
        Member member = signUpRequestDto.toEntity();

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        // 객체형태의 Response Body 생성
        return memberRepository.save(member).toResponseDto();
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public String generateAccessKey(Member member) {
        return tokenProvider.generateAccessToken(member.getAccessTokenClaims());
    }
}
