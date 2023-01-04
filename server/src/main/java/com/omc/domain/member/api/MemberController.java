package com.omc.domain.member.api;

import com.omc.domain.member.dto.LoginDto;
import com.omc.domain.member.dto.SignUpRequestDto;
import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.service.MemberService;
import com.omc.global.common.annotation.AuthMember;
import com.omc.global.config.security.entity.MemberContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping()
    public ResponseEntity<MemberResponseDto> join(@RequestBody SignUpRequestDto memberPostDto) {
        // RequestBody 를 객체화하여 MemberPostDto로 변경 후 회원가입 로직 진행
        MemberResponseDto newMember = memberService.join(memberPostDto);

        return new ResponseEntity<>(newMember, null, HttpStatus.CREATED);

//        return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponseDto> login(@RequestBody LoginDto loginDto) {
        if (loginDto.isNotValid()) {
            return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
        }

        Member member = memberService.findByEmail(loginDto.getEmail()).orElse(null);

        if (member == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }

        String accessToken = memberService.generateAccessKey(member);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authentication", accessToken);

        return MemberResponseDto.responseEntityOf(headers);
    }

    @GetMapping("/test")
    public String test(@AuthMember MemberContext memberContext) {
        return "안녕" + memberContext;
    }
}
