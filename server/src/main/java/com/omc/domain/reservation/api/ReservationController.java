package com.omc.domain.reservation.api;

import com.omc.domain.cancel.dto.CancelDto;
import com.omc.domain.member.entity.Member;
import com.omc.domain.product.service.ProductService;
import com.omc.domain.reservation.dto.ReservationDto;
import com.omc.domain.reservation.entity.Reservation;
import com.omc.domain.reservation.service.ReservationService;
import com.omc.domain.review.dto.ReviewDto;
import com.omc.domain.review.service.ReviewService;
import com.omc.global.common.dto.MultiResponse;
import com.omc.global.common.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    private final ProductService productService;
    private final ReviewService reviewService;

    // @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto.Request request) {
        // 테스트용 멤버
        Member member = productService.ifExistReturnMember(1L);

        reservationService.createReservation(request, member);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<?> getReservation(@PathVariable long reservationId) {
        ReservationDto.Response reservationResponseDto = reservationService.getResponseDto(reservationId);

        return new ResponseEntity<>(new SingleResponseDto<>(reservationResponseDto), HttpStatus.OK);
    }

    // @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "")
    public ResponseEntity<?> getReservationList(@ModelAttribute ReservationDto.PageRequest pageRequest) {
        Page<Reservation> reservationPage = reservationService.getReservationPages(pageRequest);
        List<ReservationDto.Response> responseList = reservationService.pageToResponseList(reservationPage.getContent());

        return new ResponseEntity<>(new MultiResponse<>(responseList, reservationPage), HttpStatus.OK);
    }

    //    특정 상품 예약 목록 조회
    // @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/p/{productId}")
    public ResponseEntity<?> getProductsReservationList(@PathVariable long productId,
                                                        @ModelAttribute ReservationDto.PageRequest pageRequest) {
        Page<Reservation> reservationPage = reservationService.getProductsReservationList(productId, pageRequest);
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
                                               @RequestBody CancelDto.Request request) {
        reservationService.cancelReservation(reservationId, request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
