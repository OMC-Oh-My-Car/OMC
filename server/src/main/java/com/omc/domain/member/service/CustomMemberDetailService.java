package com.omc.domain.member.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.exception.MemberNotFoundException;
import com.omc.domain.member.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@AllArgsConstructor
@Slf4j
public class CustomMemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("CustomMemberDetailService 실행");
        Member member = memberRepository.findByEmail(email).orElse(null);

        if (member == null) {
            throw new MemberNotFoundException();
        }

        return new AuthMember(member);
    }

//    private UserDetails createUserDetails(Member member) {
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getUserRole().toString());
//
//        return new User(
//            member.getEmail(),
//            member.getPassword(),
//            Collections.singleton(grantedAuthority)
//        );
//    }
}
