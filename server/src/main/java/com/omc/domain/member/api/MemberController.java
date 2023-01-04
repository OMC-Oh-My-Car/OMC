package com.omc.domain.member.api;

import com.omc.domain.member.dto.LoginDto;
import com.omc.domain.member.dto.MemberPostDto;
import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<MemberResponseDto> join(@RequestBody MemberPostDto memberPostDto) {
        // RequestBody 를 객체화하여 MemberPostDto로 변경 후 회원가입 로직 진행
        MemberResponseDto newMember = memberService.join(memberPostDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        if (loginDto.isNotValid()) {
            return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authentication", "JWT키");

        String body = "username : %s, password : %s".formatted(loginDto.getEmail(), loginDto.getPassword());

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
}
