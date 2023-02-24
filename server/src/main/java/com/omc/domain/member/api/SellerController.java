package com.omc.domain.member.api;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import com.omc.domain.member.service.MemberService;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.omc.domain.member.dto.MemberResponseDto;
import com.omc.domain.member.dto.SignUpRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SellerController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<MemberResponseDto> join(@RequestPart(value = "member") @RequestBody SignUpRequestDto signUpRequestDto, HttpServletRequest request) throws IOException {
        // 비밀번호 확인
        if (!signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordConfirm())) {
            throw new BusinessException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> imgFiles;

        if (multipartHttpServletRequest.getFiles("member").isEmpty()) {
            throw new BusinessException(ErrorCode.IMAGE_NOT_FOUND);
        } else {
            imgFiles = multipartHttpServletRequest.getFiles("member");
        }

        // RequestBody 를 객체화하여 MemberPostDto로 변경 후 회원가입 로직 진행
        memberService.sellerJoin(signUpRequestDto, imgFiles);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
