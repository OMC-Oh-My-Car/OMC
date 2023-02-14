package com.omc.domain.member.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omc.domain.member.dto.MemberModifyDto;
import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.dto.ModifyPasswordDto;
import com.omc.domain.member.dto.ReissueResponse;
import com.omc.domain.member.dto.SingleParamDto;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.exception.MemberNotFoundException;
import com.omc.domain.member.service.AuthMemberService;
import com.omc.domain.member.service.MemberService;
import com.omc.global.common.dto.SingleResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class MemberController {
    private final MemberService memberService;
    private final AuthMemberService authService;

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

    // api 활용 필요
    @PostMapping("/confirm/business-registration-number")
    public ResponseEntity<?> confirmBusinessNumber(@RequestBody SingleParamDto phoneDto) {
        String email = memberService.findByPhone(phoneDto.getParam());
        SingleResponseDto singleResponseDto = new SingleResponseDto<>(email);

        return ResponseEntity.ok(singleResponseDto);
    }
}
