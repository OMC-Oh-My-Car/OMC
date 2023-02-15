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
import org.springframework.web.bind.annotation.*;

import com.omc.domain.member.entity.Member;
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

	/**
	 * 회원가입 구현
	 * 비밀번호 확인 로직 추가
	 *
	 * @param signUpRequestDto
	 * @return memberResponseDto
	 */
	@PostMapping()
	public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) { // @Valid 어노테이션 사용하여 진행
		// 비밀번호 확인
		if (!signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordConfirm())) {
			throw new BusinessException(ErrorCode.NOT_MATCH_PASSWORD);
		}

		// RequestBody 를 객체화하여 MemberPostDto로 변경 후 회원가입 로직 진행
		MemberResponseDto memberResponseDto = memberService.signUp(signUpRequestDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/member/login")
											 .build()
											 .toUri();

		return ResponseEntity.created(uri).body(memberResponseDto);
	}

	//    @PostMapping("/login")
	//    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
	//        return ResponseEntity.ok(authService.login(loginDto, response));
	//    }

	/**
	 * 로그아웃 기능 구현
	 * 포함되어 있는 쿠키 값 유효기간 변경(0)
	 *
	 * @param request
	 * @param response
	 * @param refreshToken
	 * @param member
	 * @return Response.OK
	 */
	@PostMapping("/logout")
	public ResponseEntity<?> signOut(HttpServletRequest request, HttpServletResponse response,
									 @CookieValue(value = "refreshToken", required = false) String refreshToken,
									 @CurrentMember AuthMember member) {
		memberService.signOut(response, member.getEmail(), refreshToken);

		return ResponseEntity.ok().build();
	}

	/**
	 * 아이디 삭제 기능 구현
	 *
	 * @param authMember
	 * @return Response.OK
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@CurrentMember AuthMember authMember) {
		memberService.delete(authMember.getEmail());

		return ResponseEntity.ok().build();
	}

	/**
	 * 토큰 재발급 기능 구현
	 *
	 * @param request
	 * @param response
	 * @return Change JWT Token
	 */
	@GetMapping("/reissue")
	public ResponseEntity<ReissueResponse> reissue(@CookieValue(value = "refreshToken") String refreshToken,
												   HttpServletRequest request, HttpServletResponse response) {

		ReissueResponse reissue = memberService.reissue(refreshToken, request, response);

		return new ResponseEntity<>(reissue, null, HttpStatus.CREATED);
	}

	/**
	 * 회원정보 변경
	 *
	 * @param authMember
	 * @param memberModifyDto
	 * @return Modify Member Info
	 */
	@PatchMapping("/modify")
	public ResponseEntity<?> modify(@CurrentMember AuthMember authMember,
									@RequestBody @Valid MemberModifyDto memberModifyDto) {
		Member modifyMember = memberService.modify(authMember.getEmail(), memberModifyDto);

		return ResponseEntity.ok(modifyMember);
	}

	/**
	 * 회원 정보
	 *
	 * @param member
	 * @return Member Info
	 */
	@GetMapping("/detail")
	public ResponseEntity<MemberResponseDto> findMemberInfoByEmail(@CurrentMember AuthMember member) {
		if (member == null || member.getUsername() == null) {
			throw new BusinessException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
		}

		return ResponseEntity.ok(memberService.findByEmail(member.getUsername())
											  .map(MemberResponseDto::of)
											  .orElseThrow(() -> new RuntimeException("로그인 유저 정보 없음")));
	}

	/**
	 * 이메일 확인 인증번호 전송단계
	 *
	 * @param emailDto
	 * @return Response.OK and SendMail
	 */
	@PostMapping("/confirm/mail")
	public ResponseEntity<?> confirmEmail(@RequestBody SingleParamDto emailDto) {
		memberService.confirmMail(emailDto);

		return ResponseEntity.ok().build();
	}

	/**
	 * 이메일 확인 인증번호 인증단계
	 *
	 * @param certificationNumDto
	 * @return Response.OK if certification is OK
	 */
	@PostMapping("/certification/mail")
	public ResponseEntity<?> certificationEmail(@Valid @RequestBody SingleParamDto certificationNumDto) {
		if (!memberService.certificationMail(certificationNumDto)) {
			throw new BusinessException(ErrorCode.NOT_MATCH_CONFIRM_TEXT);
		}

		return ResponseEntity.ok().build();
	}

	/**
	 * 이메일(아이디) 검색
	 *
	 * @param mailDto
	 * @return Email
	 */
	@PostMapping("/find/id")
	public ResponseEntity<?> findEmail(@RequestBody SingleParamDto mailDto) {
		String email = memberService.findByPhone(mailDto.getParam());
		SingleResponseDto singleResponseDto = new SingleResponseDto<>(email);

		return ResponseEntity.ok(singleResponseDto);
	}

	/**
	 * 비밀번호 변경
	 * 이전 비밀번호 확인 기능 구현
	 *
	 * @param member
	 * @param modifyPasswordDto
	 * @return Response.OK and modify password
	 */
	@PatchMapping("new-pw")
	public ResponseEntity<?> newPassword(@CurrentMember AuthMember member,
										 @RequestBody ModifyPasswordDto modifyPasswordDto) {
		memberService.adaptPassword(modifyPasswordDto, member);

		return ResponseEntity.ok().build();
	}

	/**
	 * 사업자 등록번호 검증 기능
	 * API 추가 필요
	 * 수정 요망
	 *
	 * @param numberDto
	 * @return
	 */
	@PostMapping("/confirm/business-registration-number")
	public ResponseEntity<?> confirmBusinessNumber(@RequestBody SingleParamDto numberDto) {
		String email = memberService.findByPhone(numberDto.getParam());
		SingleResponseDto singleResponseDto = new SingleResponseDto<>(email);

		return ResponseEntity.ok(singleResponseDto);
	}
}
