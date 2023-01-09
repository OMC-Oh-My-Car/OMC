package com.omc.domain.reservation.api;

import com.omc.domain.reservation.dto.ReservationDto;
import com.omc.domain.reservation.entity.Reservation;
import com.omc.domain.reservation.service.ReservationService;
import com.omc.domain.review.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto.Request request) {

        reservationService.createReservation(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{reservationId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getReservation(@PathVariable long reservationId) {
        ReservationDto.Response reservationResponseDto = reservationService.getResponseDto(reservationId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getReservationList() {
        List<ReservationDto.Response> reservationDtoList = reservationService.getReservationDtoList();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
