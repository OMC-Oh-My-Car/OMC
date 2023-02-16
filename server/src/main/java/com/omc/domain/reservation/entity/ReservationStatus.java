package com.omc.domain.reservation.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
	// 0: 예약완료, 1: 예약취소, 2: 체크인, 3: 체크아웃, 4: 환불완료
	RESERVATION_COMPLETE(0L, "예약완료"),
	RESERVATION_CANCEL(1L, "예약취소"),
	CHECK_IN(2L, "체크인"),
	CHECK_OUT(3L, "체크아웃"),
	;

	private Long id;
	private String title;

	ReservationStatus(Long id, String title) {
		this.id = id;
		this.title = title;
	}
}
