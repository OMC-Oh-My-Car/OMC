package com.omc.global.jwt;

import com.omc.domain.member.dto.TokenDto;
import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.security.Key;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    /* 유저 정보로 JWT 토큰을 만들거나 토큰을 바탕으로 유저 정보를 가져옴
     *  JWT 토큰 관련 암호화, 복호화, 검증 로직
     */

    private final MemberRepository memberRepository;
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    @Value("${jwt.access-token-expiration-time}")
    private int ACCESS_TOKEN_EXPIRE_TIME;
    @Value("${jwt.refresh-token-expiration-time}")
    private int REFRESH_TOKEN_EXPIRE_TIME;
    private final Key key;

    public TokenProvider(@Value("${jwt.secretKey}") String secretKey, MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    // AccessToken 생성
    public TokenDto generateTokenWithClaims(Map<String, Object> claims, AuthMember authMember) {
        Date accessTokenExpiresIn = getTokenExpiration(ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiresIn = getTokenExpiration(REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = Jwts.builder()
                .setSubject(authMember.getEmail())
                .setClaims(claims)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .build();
    }

    public TokenDto generateTokenWithAuthentication(Authentication authentication) {
        Date accessTokenExpiresIn = getTokenExpiration(ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiresIn = getTokenExpiration(REFRESH_TOKEN_EXPIRE_TIME);

        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        Member member = memberRepository.findByEmail(userDetails.getUsername()).get();

        String accessToken = Jwts.builder()
                .setSubject(member.getEmail())
                .setClaims(member.getAccessTokenClaims())
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        Map<String, Object> claims = getClaims(accessToken);

        if(claims.get("UserRole") == null) {
            throw new RuntimeException("권한 정보 없음");
        }

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("userRole").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails principal = new User(claims.get("email").toString(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // Claims를 가져오기 위한 로직
    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch(ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            e.printStackTrace();
            throw new InvalidParameterException("유효하지 않은 토큰입니다");
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
