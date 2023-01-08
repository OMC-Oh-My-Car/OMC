package com.omc.domain.member.api;

import com.omc.domain.member.dto.*;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.service.AuthMemberService;
import com.omc.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final AuthMemberService authService;

    @PostMapping()
    public ResponseEntity<MemberResponseDto> join(@RequestBody SignUpRequestDto signUpRequestDto) {
        // RequestBody 를 객체화하여 MemberPostDto로 변경 후 회원가입 로직 진행
        MemberResponseDto newMember = authService.join(signUpRequestDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/login")
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
//        return new ResponseEntity<>(newMember, null, HttpStatus.CREATED);

//        return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(loginDto, request, response));
    }

    @GetMapping("/reissue")
    public ResponseEntity<ReissueResponse> reissue(@CookieValue(value = "refreshToken", required = false) String refreshToken,
                                                   HttpServletResponse response) {

        ReissueResponse reissue = authService.reissue(refreshToken, response);
        return new ResponseEntity<>(reissue, null, HttpStatus.CREATED);
    }
}
