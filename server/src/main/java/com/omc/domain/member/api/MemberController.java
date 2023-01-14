package com.omc.domain.member.api;

import com.omc.domain.member.dto.*;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.exception.MemberNotFoundException;
import com.omc.domain.member.exception.TokenNotFound;
import com.omc.domain.member.repository.RefreshTokenRepository;
import com.omc.domain.member.service.AuthMemberService;
import com.omc.domain.member.service.MemberService;
import com.omc.domain.member.entity.AuthMember;
import com.omc.global.common.annotation.CurrentMember;
import com.omc.global.common.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final AuthMemberService authService;
    private final RefreshTokenRepository refreshTokenRepository;

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

    @GetMapping("/reissue")
    public ResponseEntity<ReissueResponse> reissue(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = request.getHeader("Authorization");

        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        log.debug("accessToken : " + accessToken);

        ReissueResponse reissue = authService.reissue(accessToken, response);
        return new ResponseEntity<>(reissue, null, HttpStatus.CREATED);
    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody @Valid MemberModifyDto memberModifyDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw  new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        Member modifyMember = memberService.modify(authentication.getName(), memberModifyDto);

        return ResponseEntity.ok(modifyMember);
    }

    @GetMapping("/detail")
    public ResponseEntity<MemberResponseDto> findMemberInfoByEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw  new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        return ResponseEntity.ok(memberService.findByEmail(authentication.getName()).map(MemberResponseDto::of).orElseThrow(() -> new RuntimeException("로그인 유저 정보 없음")));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@CookieValue(value = "refreshToken", required = false) String refreshToken, HttpServletResponse response) {
        log.debug("refreshToken : " + refreshToken);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authService.delete(authentication.getName(), refreshToken, response);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirm/mail")
    public ResponseEntity<?> confirmEmail(@RequestBody SingleParamDto emailDto) {
        memberService.confirmMail(emailDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/find/id")
    public ResponseEntity<?> findEmail(@RequestBody SingleParamDto phoneDto) {
        String email = memberService.findByPhone(phoneDto.getParam());
        SingleResponseDto singleResponseDto = new SingleResponseDto<>(email);

        return ResponseEntity.ok(singleResponseDto);
    }

    @PatchMapping("new-pw")
    public ResponseEntity<?> newPassword(@RequestBody ModifyPasswordDto modifyPasswordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Member member = memberService.findByEmail(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        memberService.adaptPassword(modifyPasswordDto, member);

        return ResponseEntity.ok().build();
    }
}
