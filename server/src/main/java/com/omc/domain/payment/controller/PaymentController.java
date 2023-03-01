package com.omc.domain.payment.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.service.MemberService;
import com.omc.domain.payment.dto.PaymentDto;
import com.omc.domain.reservation.dto.ReservationDto;
import com.omc.domain.reservation.service.ReservationService;
import com.omc.global.common.annotation.CurrentMember;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final ReservationService reservationService;
    private final MemberService memberService;
    private final RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    private void init() {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) {
            }
        });
    }

    private final String SECRET_KEY = "test_sk_D4yKeq5bgrpxPylPpbxrGX0lzW6Y";

    @RequestMapping(value = "/success", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> confirmPayment(
            @RequestBody PaymentDto.Request payRequest,
            @CurrentMember AuthMember member,
            Model model) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(SECRET_KEY, ""); // spring framework 5.2 이상 버전에서 지원
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("orderId", payRequest.getOrderId());
        payloadMap.put("amount", String.valueOf(payRequest.getAmount()));

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://api.tosspayments.com/v1/payments/" + payRequest.getPaymentKey(), request, JsonNode.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            JsonNode successNode = responseEntity.getBody();
            model.addAttribute("orderId", successNode.get("orderId").asText());
            String secret = successNode.get("secret").asText(); // 가상계좌의 경우 입금 callback 검증을 위해서 secret을 저장하기를 권장함
            Member findMember = memberService.findByEmail(member.getEmail())
                    .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

            ReservationDto.Request reservationRequest = ReservationDto.Request.builder()
                    .productId(payRequest.getProductId())
                    .phoneNumber(payRequest.getPhoneNumber())
                    .startDate(payRequest.getStartDate())
                    .endDate(payRequest.getEndDate())
                    .build();

            reservationService.createReservation(reservationRequest, findMember);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            JsonNode failNode = responseEntity.getBody();
            model.addAttribute("message", failNode.get("message").asText());
            model.addAttribute("code", failNode.get("code").asText());
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
    }

    @RequestMapping("/fail")
    public String failPayment(@RequestParam String message, @RequestParam String code, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("code", code);
        return "fail";
    }

    @RequestMapping("/virtual-account/callback")
    @ResponseStatus(HttpStatus.OK)
    public void handleVirtualAccountCallback(@RequestBody CallbackPayload payload) {
        if (payload.getStatus().equals("DONE")) {
            // handle deposit result
        }
    }

    private static class CallbackPayload {
        public CallbackPayload() {
        }

        private String secret;
        private String status;
        private String orderId;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }
}
