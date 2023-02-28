package com.omc.domain.reservation.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omc.domain.cancel.dto.CancelDto;
import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.service.MemberService;
import com.omc.domain.product.service.ProductService;
import com.omc.domain.reservation.dto.ReservationDto;
import com.omc.domain.reservation.entity.Reservation;
import com.omc.domain.reservation.service.ReservationService;
import com.omc.domain.review.dto.ReviewDto;
import com.omc.domain.review.service.ReviewService;
import com.omc.global.common.annotation.CurrentMember;
import com.omc.global.common.dto.MultiResponse;
import com.omc.global.common.dto.SingleResponseDto;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    private final ProductService productService;
    private final ReviewService reviewService;
    private final MemberService memberService;

    // @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto.Request reservationRequest,
                                               @CurrentMember AuthMember member) {
        Member findMember = memberService.findByEmail(member.getEmail())
                                         .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));
        reservationService.createReservation(reservationRequest, findMember);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/check", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> canReservation(@RequestBody ReservationDto.Request reservationRequest
    ) {

        ReservationDto.CanReservationRes canReservationRes = reservationService.canReservation(reservationRequest);

        return new ResponseEntity<>(new SingleResponseDto<>(canReservationRes), HttpStatus.OK);
    }

    // @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<?> getReservation(@PathVariable long reservationId) {
        ReservationDto.DetailDto reservationResponseDto = reservationService.getResponseDto(reservationId);

        return new ResponseEntity<>(new SingleResponseDto<>(reservationResponseDto), HttpStatus.OK);
    }

    // @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "")
    public ResponseEntity<?> getReservationList(@ModelAttribute ReservationDto.Search search) {
        Page<Reservation> reservationPage = reservationService.getReservationPages(search);
        List<ReservationDto.Response> responseList = reservationService.pageToResponseList(reservationPage.getContent());

        return new ResponseEntity<>(new MultiResponse<>(responseList, reservationPage), HttpStatus.OK);
    }

    // @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/my")
    public ResponseEntity<?> getMyReservationList(@ModelAttribute ReservationDto.Search search, @CurrentMember AuthMember member) {
        Member findMember = memberService.findByEmail(member.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

        Page<Reservation> reservationPage = reservationService.getMyReservationPages(search, findMember);
        List<ReservationDto.Response> responseList = reservationService.pageToResponseList(reservationPage.getContent());

        return new ResponseEntity<>(new MultiResponse<>(responseList, reservationPage), HttpStatus.OK);
    }

    //    특정 상품 예약 목록 조회
    // @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/p/{productId}")
    public ResponseEntity<?> getProductsReservationList(@PathVariable long productId,
                                                        @ModelAttribute ReservationDto.Search search) {
        Page<Reservation> reservationPage = reservationService.getProductsReservationList(productId, search);
        List<ReservationDto.Response> responseList = reservationService.pageToResponseList(reservationPage.getContent());

        return new ResponseEntity<>(new MultiResponse<>(responseList, reservationPage), HttpStatus.OK);
    }

    // @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{reservationId}/cancel-reason")
    public ResponseEntity<?> getCancelReason(@PathVariable long reservationId) {
        CancelDto.Response response = reservationService.getCancelReason(reservationId);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    // @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{reservationId}/review")
    public ResponseEntity<?> getReservationsReview(@PathVariable long reservationId) {
        ReviewDto.Response reviewResponse = reviewService.findByResId(reservationId);

        return new ResponseEntity<>(new SingleResponseDto<>(reviewResponse), HttpStatus.OK);
    }

    // @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/cancel/{reservationId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> cancelReservation(@PathVariable long reservationId,
                                               @RequestBody CancelDto.Request request,
                                               @CurrentMember AuthMember member) {

        Member findMember = memberService.findByEmail(member.getEmail())
                                         .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));
        reservationService.cancelReservation(reservationId, request, findMember);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
