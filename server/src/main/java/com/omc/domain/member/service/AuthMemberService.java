package com.omc.domain.member.service;

import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.dto.SignUpRequestDto;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.exception.DuplicateEmail;
import com.omc.domain.member.exception.DuplicateNickname;
import com.omc.domain.member.exception.DuplicateUsername;
import com.omc.domain.member.repository.MemberRepository;
import com.omc.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthMemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto join(SignUpRequestDto signUpRequestDto) {
        System.out.println(signUpRequestDto.getEmail());
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
}
