package com.omc.domain.member.api;

import com.omc.domain.member.dto.LoginDto;
import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.dto.SignUpRequestDto;
import com.omc.domain.member.dto.TokenDto;
import com.omc.domain.member.exception.MemberNotFoundException;
import com.omc.domain.member.service.AuthMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
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
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(loginDto, response));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value = "refreshToken", required = false) String refreshToken, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) {
            throw new MemberNotFoundException();
        }

        authService.logout(authentication.getName(), refreshToken, response);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@CookieValue(value = "refreshToken", required = false) String refreshToken, HttpServletResponse response) {
        log.debug("refreshToken : " + refreshToken);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authService.delete(authentication.getName(), refreshToken, response);

        return ResponseEntity.ok().build();
    }
}
