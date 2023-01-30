package com.omc.domain.member.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.omc.domain.member.dto.*;
import com.omc.domain.member.entity.AuthMember;
import com.omc.global.common.annotation.CurrentMember;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.omc.domain.member.entity.Member;
import com.omc.domain.member.exception.MemberNotFoundException;
import com.omc.domain.member.service.MemberService;
import com.omc.global.common.dto.SingleResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<?> join(@RequestBody SignUpRequestDto signUpRequestDto) {
        if (!signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordConfirm())) {
            throw new BusinessException(ErrorCode.NOT_MATCH_PASSWORD);// 패스워드 일치하지 않음 에러
        }
        // RequestBody 를 객체화하여 MemberPostDto로 변경 후 회원가입 로직 진행
        memberService.join(signUpRequestDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/login")
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
//        return new ResponseEntity<>(newMember, null, HttpStatus.CREATED);

//        return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
    }

//    @PostMapping("/login")
//    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
//        return ResponseEntity.ok(authService.login(loginDto, response));
//    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CurrentMember AuthMember member) {
        memberService.logout(member.getEmail());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@CurrentMember AuthMember authMember) {
        memberService.delete(authMember.getEmail());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/reissue")
    public ResponseEntity<ReissueResponse> reissue(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = request.getHeader("Authorization");

        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        log.debug("accessToken : " + accessToken);

        ReissueResponse reissue = memberService.reissue(accessToken, response);
        return new ResponseEntity<>(reissue, null, HttpStatus.CREATED);
    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modify(@CurrentMember AuthMember authMember, @RequestBody @Valid MemberModifyDto memberModifyDto) {
        Member modifyMember = memberService.modify(authMember.getEmail(), memberModifyDto);

        return ResponseEntity.ok(modifyMember);
    }

    @GetMapping("/detail")
    public ResponseEntity<MemberResponseDto> findMemberInfoByEmail(@CurrentMember AuthMember member) {
        if (member == null || member.getUsername() == null) {
            throw  new BusinessException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        return ResponseEntity.ok(memberService.findByEmail(member.getUsername()).map(MemberResponseDto::of).orElseThrow(() -> new RuntimeException("로그인 유저 정보 없음")));
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
    public ResponseEntity<?> newPassword(@CurrentMember AuthMember member, @RequestBody ModifyPasswordDto modifyPasswordDto) {
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
