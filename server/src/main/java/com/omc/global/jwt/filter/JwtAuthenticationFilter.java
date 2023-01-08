//package com.omc.global.jwt.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.omc.domain.member.dto.LoginDto;
//import com.omc.domain.member.dto.SignUpRequestDto;
//import com.omc.domain.member.dto.TokenDto;
//import com.omc.domain.member.entity.AuthMember;
//import com.omc.domain.member.entity.Member;
//import com.omc.domain.member.exception.DuplicateEmail;
//import com.omc.domain.member.repository.MemberRepository;
//import com.omc.global.jwt.TokenProvider;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseCookie;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFilter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Slf4j
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final TokenProvider tokenProvider;
//    private final AuthenticationManager authenticationManager;
//    private final MemberRepository memberRepository;
//
//    @SneakyThrows
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response) throws AuthenticationException {
//        log.debug("start AuthenticationFilter");
//        ObjectMapper om = new ObjectMapper();
////        SignUpRequestDto signUpRequest = om.readValue(request.getInputStream(), SignUpRequestDto.class);
//        LoginDto loginDto = om.readValue(request.getInputStream(), LoginDto.class);
//        log.debug("loginDtoEmail : " + loginDto.getEmail());
//        log.debug("loginDtoPassword : " + loginDto.getPassword());
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
//
//        if (authenticationToken.isAuthenticated()) {
//            log.debug("success");
//        } else {
//            log.debug("failed");
//        }
//
//        return authenticationManager.authenticate(authenticationToken);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//
//        AuthMember authMember = (AuthMember) authResult.getPrincipal();
//        log.debug("authMember : " + authMember.getEmail());
//
//        Member member = memberRepository.findByEmail(authMember.getEmail())
//                .orElseThrow(DuplicateEmail::new); // 수정 예정
//
//
//        TokenDto tokenDto = tokenProvider.generateToken(member.getAccessTokenClaims(), authMember);
//        member.setAccessToken(tokenDto.getAccessToken());
//
//        String refreshToken = tokenDto.getRefreshToken();
//
//        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
//                .maxAge(7 * 24 * 60 * 60)
//                .path("/")
//                .secure(true)
//                .sameSite("None")
//                .httpOnly(true)
//                .build();
//        response.setHeader("Set-Cookie", cookie.toString());
//
//        response.setHeader("Authentication", "Bearer " + tokenDto.getAccessToken());
//
//        // response body에 member의 emial, username, ImageUrl을 담아서 보내준다.
//        response.getWriter().write(
//                "{" +
//                        "\"email\":\"" + member.getEmail() + "\","
//                        + "\"username\":\"" + member.getUsername() + "\","
//                        + "\"nickname\":\"" + member.getNickname() + "\"" +
//                        "}"
//        );
//
//
//        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
//    }
//}
