package com.omc.domain.member.service;

import com.omc.domain.member.dto.MemberPostDto;
import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDto join(MemberPostDto memberPostDto) {
        Member member = memberPostDto.toEntity();

        return memberRepository.save(member).toResponseDto();
    }
}
