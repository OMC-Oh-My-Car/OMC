package com.omc.global.jwt.filter;

import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.repository.MemberRepository;
import com.omc.global.jwt.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * JWT 인증을 담당
 */
@AllArgsConstructor
@Slf4j
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;


    /**
     * JWT 토큰을 검증하는 함수
     *
     * @param request     요청
     * @param response    응답
     * @param filterChain 필터 체인
     */
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {
        log.debug("JwtVerificationFilter 실행");

        try {
            Claims claims = verifyJws(request);
            setAuthenticationToContext(claims);

        } catch (SignatureException se) {
            throw new JwtException("사용자 인증 실패", se);
        } catch (ExpiredJwtException ee) {
            throw new JwtException("토큰 기한 만료", ee);
        } catch (Exception e) {
            throw new JwtException("토큰 검증 실패", e);
        }

        filterChain.doFilter(request, response);

        log.debug("JwtVerificationFilter 종료");
    }

    /**
     * 액세스 토큰을 검증하는 함수
     *
     * @param request 요청
     * @return 토큰 검증 결과
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        log.debug("JwtVerificationFilter - shouldNotFilter");

        String authorization = request.getHeader("Authorization");
        boolean bearer = authorization == null || !authorization.startsWith("Bearer");

        log.debug("shouldNotFilter : {}", bearer);

        return bearer;
    }

    /**
     * request 에서 claims 를 추출하는 함수
     *
     * @param request 요청
     * @return 토큰 검증 결과
     */
    private Claims verifyJws(HttpServletRequest request) {
        log.debug("JwtVerificationFilter - verifyJws");

        String jws = request.getHeader("Authorization").replace("Bearer ", "");

        Claims claims = tokenProvider.getClaims(jws);

        log.debug("verifyJws 통과");

        return claims;
    }

    /**
     * SecurityContext 에 Authentication 을 저장하는 함수
     *
     * @param claims 토큰 검증 결과
     */
    private void setAuthenticationToContext(Claims claims) {
        log.debug("JwtVerificationFilter - setAuthenticationToContext");

        String emaIl = claims.get("email").toString();
        log.debug("서명이 정상적으로 됌" + emaIl);
        Member member = memberRepository.findByEmail(emaIl).get();

        AuthMember memberDetails = new AuthMember(member);

        Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.debug("setAuthenticationToContext 통과");
    }
}