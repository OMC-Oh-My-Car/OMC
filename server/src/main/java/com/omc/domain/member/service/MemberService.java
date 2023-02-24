package com.omc.domain.member.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.omc.domain.img.dto.ImgDto;
import com.omc.domain.img.entity.MemberImg;
import com.omc.domain.img.repository.MemberImgRepository;
import com.omc.domain.member.dto.*;
import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.RefreshToken;
import com.omc.domain.member.entity.UserRole;
import com.omc.domain.member.repository.RefreshTokenRepository;
import com.omc.global.error.ErrorCode;
import com.omc.global.error.exception.BusinessException;
import com.omc.global.jwt.TokenProvider;
import com.omc.global.util.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.omc.domain.member.entity.Member;
import com.omc.domain.member.exception.MemberNotFoundException;
import com.omc.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender emailSender;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final MemberImgRepository memberImgRepository;
    private final S3Service s3Service;

    String confirmText = "";

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public void signUp(SignUpRequestDto signUpRequestDto, List<MultipartFile> imgFiles) throws IOException {
        if (memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        if (memberRepository.existsByNickname(signUpRequestDto.getNickname())) {
            throw new BusinessException(ErrorCode.DUPLICATE_NICKNAME);
        }

        List<MemberImg> memberImgList = uploadImgAndImgDtoToEntity(imgFiles);

        Member member = signUpRequestDto.encodePasswordSignUp(passwordEncoder, memberImgList);
        // 관리자 전용 테스트 아이디 생성
        if (signUpRequestDto.getEmail().equals("admin@omc.com")) {
            member.setUserRole(UserRole.ROLE_ADMIN);
        }

        memberRepository.save(member);
    }

    public void sellerJoin(SignUpRequestDto signUpRequestDto, List<MultipartFile> imgFiles) throws IOException {
        if (memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        if (memberRepository.existsByNickname(signUpRequestDto.getNickname())) {
            throw new BusinessException(ErrorCode.DUPLICATE_NICKNAME);
        }

        List<MemberImg> memberImgList = uploadImgAndImgDtoToEntity(imgFiles);

        Member member = signUpRequestDto.encodePasswordSellerSignUp(passwordEncoder, memberImgList);

        memberRepository.save(member);
    }

//    public TokenDto login(LoginDto loginDto, HttpServletResponse response) throws IOException {
//        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication();
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//        AuthMember authMember = (AuthMember) authentication.getPrincipal();
//
//        TokenDto tokenDto = tokenProvider.generateTokenWithAuthentication(authentication);
//
//        ResponseCookie cookie = ResponseCookie.from("refreshToken", tokenDto.getRefreshToken())
////                .domain(".localhost:3000/")
//                .maxAge(7 * 24 * 60 * 60)
//                .path("/")
//                .secure(true)
//                .sameSite("None")
//                .httpOnly(true)
//                .build();
//
//        // 회원탈퇴 및 로그인 처리를 위해 사용.
//        // cookie의 refreshToken값과 비교하여 없을 경우 로그아웃 및 회원탈퇴 처리에 이용
//        response.setHeader("Set-Cookie", cookie.toString());
////        response.setHeader("Set-Cookie", tokenDto.getRefreshToken());
//        // TokenDto의 accessToken을 Header의 Authorization이름으로 넣어줌
//        response.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
//
//        // *
//        // *
//        // example
////        response.getWriter().write(
////                "{" +
////                        "\"email\":\"" + authMember.getMember().getEmail() + "\","
////                        +  "\"username\":\"" + authMember.getMember().getUsername() + "\","
////                        + "\"nickname\":\"" + authMember.getMember().getNickname() + "\"" +
////                        "}"
////        );
//
//        RefreshToken refreshToken = RefreshToken.builder()
//                .key(loginDto.getEmail())
//                .value(tokenDto.getRefreshToken())
//                .build();
//
//        refreshTokenRepository.save(refreshToken);
//
//        // 토큰 발급
//        return tokenDto;
//    }

    public ReissueResponse reissue(AuthMember member, HttpServletRequest request, HttpServletResponse response) {
        RefreshToken refreshToken = refreshTokenRepository.findByKey(member.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXISTS_AUTHORIZATION));

        String savedRefreshToken = refreshToken.getValue();
        log.debug("refreshToken : " + savedRefreshToken);

        if (!tokenProvider.validateToken(savedRefreshToken)) {
            throw new BusinessException(ErrorCode.NOT_MATCH_REFRESH_TOKEN);
        }

        TokenDto tokenDto = tokenProvider.generateTokenWithAuthentication(member);
        String newRT = tokenDto.getRefreshToken();
        String newAT = tokenDto.getAccessToken();

//        if (!savedRefreshToken.getValue().equals(refreshToken)) {
//            throw new TokenInvalid();
//        }

        RefreshToken newRefreshToken = refreshToken.updateValue(newRT);
        refreshTokenRepository.save(newRefreshToken);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", newRT)
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());

        response.setHeader("Authorization", "Bearer " + newAT);

        return ReissueResponse.toResponse(member);
    }

    public void signOut(HttpServletResponse response, String email, String refreshToken) {
        if (refreshToken == null) {
            refreshToken = refreshTokenRepository.findByKey(email).orElse(null).getValue();
        }

        // refreshToken 존재 및 유효성 분류
        if (refreshToken != null && !tokenProvider.validateToken(refreshToken)) {
            throw new BusinessException(ErrorCode.NOT_VALID_TOKEN);
        }

        if (!memberRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.MEMBER_NOT_EXISTS);
        }

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(0)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());

        refreshTokenRepository.deleteByKey(email);
    }

    public void delete(String email) {
        // 회원 존재 파악
        if (!memberRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.MEMBER_NOT_EXISTS);
        }

        RefreshToken refreshToken = refreshTokenRepository.findByKey(email).orElse(null);

        // refreshToken 존재 및 유효성 분류
        if (refreshToken != null && !tokenProvider.validateToken(refreshToken.getValue())) {
            throw new BusinessException(ErrorCode.NOT_VALID_TOKEN);
        }

        memberRepository.deleteByEmail(email);
        refreshTokenRepository.deleteByKey(email);
    }

    public Member modify(String email, MemberModifyDto memberModifyDto, List<MultipartFile> imgFiles) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        if (member == null) {
            throw new BusinessException(ErrorCode.TOKEN_EXPIRED);
        }

        member.patch(memberModifyDto);

        if (imgFiles.size() != 0) {
            memberImgRepository.deleteByMemberId(member.getId());
            member.getMemberImgList().stream().map(MemberImg::getImgName).forEach(s3Service::deleteFile);
            List<MemberImg> memberImgs = uploadImgAndImgDtoToEntity(imgFiles);
            member.setProductImgList(memberImgs);
        }
//        log.debug("new MemberEmail : " + newMember.getEmail());
        return memberRepository.save(member);
    }

    public void confirmMail(SingleParamDto emailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("OMC@OMC.com");
        message.setTo(emailDto.getParam());

        // *
        // 랜덤 문자열 + 숫자 지정
        int leftLimit = 48; // 숫자형
        int rightLimit = 122; // 문자형
        int targetStringLength = 10; // 자리수 지정
        Random random = new Random();

        confirmText = random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        // *
        String text = "이메일 인증 번호 : " + confirmText;
        log.debug(text);
        message.setSubject("OMC 이메일 인증입니다.");
        message.setText(text);
        emailSender.send(message);
    }

    public boolean certificationMail(SingleParamDto certificationMail) {
        if (!certificationMail.getParam().equals(confirmText)) {
            return false;
        }

        return true;
    }

    public String findByPhone(String param) {
            Member member = memberRepository.findByPhone(param).orElse(null);
            if (member == null) {
                throw new BusinessException(ErrorCode.MEMBER_NOT_EXISTS);
            }
            return member.getEmail();
    }

    public void adaptPassword(ModifyPasswordDto modifyPasswordDto, AuthMember member) {
        if (member == null) {
            throw new BusinessException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        if (!passwordEncoder.matches(modifyPasswordDto.getOldPassword(), member.getPassword())) {
            throw new BusinessException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        String encryptedPassword = passwordEncoder.encode(modifyPasswordDto.getNewPassword());

        member.setPassword(encryptedPassword);
        memberRepository.save(member);
    }

    private List<MemberImg> uploadImgAndImgDtoToEntity(List<MultipartFile> imgFiles) {
        List<MemberImg> memberImgList = new ArrayList<>();
        List<ImgDto.Request> imgDtoList = new ArrayList<>();

        for (MultipartFile img : imgFiles) {
            ImgDto.Request imgDto = s3Service.uploadImage(img, "member");
            imgDtoList.add(imgDto);
        }

        imgDtoToImg(memberImgList, imgDtoList);

        return memberImgList;
    }

    private static void imgDtoToImg(List<MemberImg> memberImgList, List<ImgDto.Request> imgDtoList) {
        for (ImgDto.Request imgDto : imgDtoList) {
            MemberImg memberImg = MemberImg.builder()
                    .imgName(imgDto.getImgName())
                    .imgUrl(imgDto.getImgUrl())
                    .build();
            memberImgList.add(memberImg);
        }
    }
}
