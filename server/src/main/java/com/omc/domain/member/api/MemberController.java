package com.omc.domain.member.api;

import com.omc.domain.member.dto.MemberPostDto;
import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
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
        MemberResponseDto newMember = memberService.join(memberPostDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
    }
}
