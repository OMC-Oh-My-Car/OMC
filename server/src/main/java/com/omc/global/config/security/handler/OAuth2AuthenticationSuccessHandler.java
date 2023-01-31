package com.omc.global.config.security.handler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omc.global.common.annotation.CurrentMember;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.omc.domain.member.dto.TokenDto;
import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.repository.MemberRepository;
import com.omc.global.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication, @CurrentMember AuthMember authMember) throws IOException {

//        login 성공한 사용자 목록.
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        Map<String, Object> kakao_account = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
        String email = (String) kakao_account.get("email");
        Map<String, Object> properties = (Map<String, Object>) oAuth2User.getAttributes().get("properties");

        Member member = memberRepository.findByEmail(email).orElseThrow();
        TokenDto tokenDto = tokenProvider.generateTokenWithClaims(member.getAccessTokenClaims(), authMember);


        String url = makeRedirectUrl(tokenDto.getAccessToken());
        System.out.println("url: " + url);

        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }

        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String token) {
        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/redirect/")
                .queryParam("AccessToken", token)
                .build().toUriString();
    }
}
