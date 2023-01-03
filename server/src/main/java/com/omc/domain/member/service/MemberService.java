package com.omc.domain.member.service;

import com.omc.domain.member.dto.MemberPostDto;
import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto join(MemberPostDto memberPostDto) {
        // 객체화된 Request Body의 value로 Member Entity를 Build
        Member member = memberPostDto.toEntity();

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        // 객체형태의 Response Body 생성
        return memberRepository.save(member).toResponseDto();
    }
}
