package com.omc.domain.member.service;

import com.omc.domain.member.dto.MemberModifyDto;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.exception.DuplicateEmail;
import com.omc.domain.member.exception.MemberNotFoundException;
import com.omc.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Member modify(String email, MemberModifyDto memberModifyDto) {
        if (memberRepository.existsByEmail(memberModifyDto.getEmail())) {
            throw new DuplicateEmail();
        }

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFoundException());
        member.patch(memberModifyDto);
//        log.debug("new MemberEmail : " + newMember.getEmail());
        return memberRepository.save(member);
    }
}
