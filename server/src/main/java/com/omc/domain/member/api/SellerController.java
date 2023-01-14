package com.omc.domain.member.api;

import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.dto.SignUpRequestDto;
import com.omc.domain.member.service.AuthMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
@Slf4j
public class SellerController {
    private final AuthMemberService authService;

    @PostMapping()
    public ResponseEntity<MemberResponseDto> join(@RequestBody SignUpRequestDto signUpRequestDto) {
        // RequestBody 를 객체화하여 MemberPostDto로 변경 후 회원가입 로직 진행
        MemberResponseDto newMember = authService.sellerJoin(signUpRequestDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/login")
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
