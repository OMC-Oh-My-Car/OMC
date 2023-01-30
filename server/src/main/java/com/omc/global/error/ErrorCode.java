package com.omc.global.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	TEST(HttpStatus.INTERNAL_SERVER_ERROR, "001", "business exception test"),

	// 인증 && 인가
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001", "토큰이 만료되었습니다."),
	NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "해당 토큰은 유효한 토큰이 아닙니다."),
	NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-003", "Authorization Header가 빈값입니다."),
	NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A-004", "인증 타입이 Bearer 타입이 아닙니다."),
	REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A-005", "해당 refresh token은 존재하지 않습니다."),
	REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-006", "해당 refresh token은 만료됐습니다."),
	NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-007", "해당 토큰은 ACCESS TOKEN이 아닙니다."),
	FORBIDDEN_ADMIN(HttpStatus.FORBIDDEN, "A-008", "관리자 Role이 아닙니다."),
	NOT_MATCH_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "A-009", "해당 refresh token과 일치하지 않습니다."),

	// 회원
	INVALID_MEMBER_TYPE(HttpStatus.BAD_REQUEST, "M-001", "잘못된 회원 타입 입니다.(memberType : KAKAO)"),
	ALREADY_REGISTERED_MEMBER(HttpStatus.BAD_REQUEST, "M-002", "이미 가입된 회원 입니다."),
	MEMBER_NOT_EXISTS(HttpStatus.BAD_REQUEST, "M-003", "해당 회원은 존재하지 않습니다."),
	DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "M-004", "이미 가입되어 있는 이메일 입니다."),
	DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "M-006", "이미 가입되어 있는 닉네임 입니다."),
	NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "M-004", "비밀번호가 일치하지 않습니다."),

	// 상품
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P-001", "해당 상품은 존재하지 않습니다."),

	// 리뷰
	REVIEW_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "R-001", "리뷰가 이미 존재합니다."),
	REVIEW_NOT_FOUND(HttpStatus.BAD_REQUEST, "R-002", "해당 리뷰가 존재하지 않습니다."),
	NOT_YET_CHECKIN(HttpStatus.BAD_REQUEST, "R-003", "체크인 이후부터 리뷰를 작성할 수 있습니다."),
	CANCEL_CANT_WRITE(HttpStatus.BAD_REQUEST, "R-004", "환불한 상품은 리뷰를 작성할 수 없습니다."),

	// 이미지
	IMAGE_NOT_FOUND(HttpStatus.BAD_REQUEST, "I-001", "이미지 파일이 존재하지 않습니다."),
	WRONG_INPUT_IMAGE(HttpStatus.BAD_REQUEST, "I-002", "이미지 파일이 아닙니다."),
	WRONG_IMAGE_FORMAT(HttpStatus.BAD_REQUEST, "I-003", "이미지 파일 형식이 잘못되었습니다."),
	IMAGE_UPLOAD_ERROR(HttpStatus.BAD_REQUEST, "I-004", "이미지 업로드에 실패했습니다."),

	// 예약
	RESERVATION_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "R-001", "해당 예약이 이미 존재합니다."),
	RESERVATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "R-002", "해당 예약이 존재하지 않습니다."),
	CANT_RESERVATION(HttpStatus.BAD_REQUEST, "R-003", "예약할 수 없는 날짜입니다."),

	// 취소
	CANCEL_NOT_FOUND(HttpStatus.BAD_REQUEST, "C-002", "해당 예약이 존재하지 않습니다.")
	;


	private HttpStatus httpStatus;
	private String errorCode;
	private String message;

	ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}

}
