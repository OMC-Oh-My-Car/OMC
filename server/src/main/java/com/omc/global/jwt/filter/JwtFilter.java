package com.omc.global.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.omc.global.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;


    /* 실제 필터링 로직은 doFilterInternal 에서 수행
    JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할
    가입/로그인/재발급을 제외한 Request 요청은 모두 이 필터를 거치게 됨
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Request Header 에서 JWT 를 받아옴
        String jwt = resolveToken(request);
        log.debug("token : " + jwt);

        // 토큰의 유효성 확인
        try {
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                // 토큰으로부터 Authentication 객체를 만듬
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                // SecurityContext 에 Authentication 객체를 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            e.printStackTrace();

//            if (e instanceof BusinessException) {
//                ObjectMapper objectMapper = new ObjectMapper();
//                String json = objectMapper.writeValueAsString(ErrorResponse.of(((BusinessException)e).getErrorCode()));
//                response.getWriter().write(json);
//                response.setStatus(((BusinessException)e).getErrorCode().getStatus());
//
//            }

        }

    }

    // Request Header 에서 토큰 정보를 꺼내오는 메소드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }

        return bearerToken;
    }
}
//        String token = request.getHeader("Authentication");
//        log.debug("token : " + token);
//
//        if (token != null) {
//            if (tokenProvider.verify(token)) {
//                Map<String, Object> claims = tokenProvider.getClaims(token);
//                String email = (String) claims.get("email");
//                Member member = memberService.findByEmail(email).orElseThrow(
//                        () -> new UsernameNotFoundException("'%s' email not found.".formatted(email))
//                );
//
//                // 2차 체크(화이트리스트에 포함되는지)
//                if ( memberService.verifyWithWhiteList(member, token) ) {
//                    AuthMember authMember = AuthMember.of(member);
//
//                    TokenDto tokenDto = tokenProvider.generateToken(member.getAccessTokenClaims(), authMember);
//                    String refreshToken = tokenDto.getRefreshToken();
//
//                    ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
//                            .maxAge(7 * 24 * 60 * 60)
//                            .path("/")
//                            .secure(true)
//                            .sameSite("None")
//                            .httpOnly(true)
//                            .build();
//                    response.setHeader("Set-Cookie", cookie.toString());
//                    response.setHeader("Authentication", "Bearer " + tokenDto.getAccessToken());
//
//                    Authentication authentication = tokenProvider.getAuthentication(token);
//
//                    SecurityContext context = SecurityContextHolder.createEmptyContext();
//                    context.setAuthentication(authentication);
//                    SecurityContextHolder.setContext(context);
//                }
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
