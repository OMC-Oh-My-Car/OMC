package com.omc.domain.member.service;

import com.omc.domain.member.dto.MemberModifyDto;
import com.omc.domain.member.dto.ModifyPasswordDto;
import com.omc.domain.member.dto.SingleParamDto;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.exception.DuplicateEmail;
import com.omc.domain.member.exception.MemberNotFoundException;
import com.omc.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender emailSender;

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

    public void confirmMail(SingleParamDto emailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("OMC@OMC.com");
        message.setTo(emailDto.getParam());
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String confirmText = new String(array, Charset.forName("UTF-8"));
        String text = "이메일 인증 번호 : " + confirmText;
        message.setSubject("OMC 이메일 인증입니다.");
        message.setText(text);
        emailSender.send(message);
    }

    public String findByPhone(String param) {
            Member member = memberRepository.findByPhone(param).orElseThrow(MemberNotFoundException::new);

            return member.getEmail();
    }

    public void adaptPassword(ModifyPasswordDto modifyPasswordDto, Member member) {
        if (!passwordEncoder.matches(modifyPasswordDto.getOldPassword(), member.getPassword())) {
            throw new RuntimeException();
        }

        String encryptedPassword = passwordEncoder.encode(modifyPasswordDto.getNewPassword());

        member.setPassword(encryptedPassword);
        memberRepository.save(member);
    }
}
