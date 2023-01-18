package com.omc.global.config.security.handler;

import com.omc.domain.member.dto.TokenDto;
import com.omc.domain.member.entity.AuthMember;
import com.omc.domain.member.entity.Member;
import com.omc.domain.member.repository.MemberRepository;
import com.omc.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private MemberRepository memberRepository;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

//        login 성공한 사용자 목록.
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        Map<String, Object> kakao_account = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
        String email = (String) kakao_account.get("email");
        Map<String, Object> properties = (Map<String, Object>) oAuth2User.getAttributes().get("properties");
        String nickname = (String) properties.get("nickname");

        Member member = memberRepository.findByEmail(email).orElseThrow();
        AuthMember authMember = AuthMember.of(member);
        TokenDto tokenDto = tokenProvider.generateTokenWithClaims(member.getAccessTokenClaims(), authMember);


//        String url = makeRedirectUrl(jwt);
//        System.out.println("url: " + url);
//
//        if (response.isCommitted()) {
//            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
//            return;
//        }
//        getRedirectStrategy().sendRedirect(request, response, url);
    }

//    private String makeRedirectUrl(String token) {
//        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/redirect/"+token)
//                .build().toUriString();
//    }
}
