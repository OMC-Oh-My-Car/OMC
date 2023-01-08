package com.omc.domain.member.service;

import com.omc.domain.member.dto.SignUpRequestDto;
import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.dto.TokenDto;
import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.exception.DuplicateEmail;
import com.omc.domain.member.exception.DuplicateNickname;
import com.omc.domain.member.exception.DuplicateUsername;
import com.omc.domain.member.repository.MemberRepository;
import com.omc.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public boolean verifyWithWhiteList(Member member, String token) {
        return member.getAccessToken().equals(token);
    }
}
