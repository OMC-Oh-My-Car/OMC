package com.omc.global.config.security.service;

import com.omc.domain.member.entity.Member;
import com.omc.domain.member.entity.Social;
import com.omc.domain.member.entity.UserRole;
import com.omc.domain.member.exception.MemberNotFoundException;
import com.omc.domain.member.repository.MemberRepository;
import com.omc.domain.member.service.MemberService;
import com.omc.global.error.exception.MemberAlreadyExist;
import com.omc.global.error.exception.OAuthTypeMatchNotFoundException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String oauthId = oAuth2User.getName();
        log.debug("oauthId : " + oauthId);

        Member member = null;
        String oauthType = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
        if (!"KAKAO".equals(oauthType)) {
            throw new OAuthTypeMatchNotFoundException();
        }
        if (isNew(oauthType, oauthId)) {
            switch (oauthType) {
                case "KAKAO" -> {
                    log.debug("attributes : " + attributes);

                    Map attributesProperties = (Map) attributes.get("properties");
                    Map attributesKakaoAcount = (Map) attributes.get("kakao_account");
                    String nickname = (String) attributesProperties.get("nickname");
                    String email = (String) attributesProperties.get("email");
                    String username = "%s_%s".formatted(oauthType, oauthId);
                    String profile_image = (String) attributesProperties.get("profile_image");
                    if ((boolean) attributesKakaoAcount.get("has_email")) {
                        email = (String) attributesKakaoAcount.get("email");
                    }

                    if (memberRepository.findByEmail(email).isPresent()) {
                        throw new MemberAlreadyExist();
                    } else {
                        member = Member.builder()
                                .email(email)
                                .username(username)
                                .password("")
                                .profileImg(profile_image)
                                .nickname(nickname)
                                .isSocial(Social.KAKAO)
                                .phone("")
                                .userRole(UserRole.ROLE_USER)
                                .build();
                        memberRepository.save(member);
                    }
                }
            }
        } else {
            member = memberRepository.findByEmail("%s_%s".formatted(oauthType, oauthId))
                    .orElseThrow(MemberNotFoundException::new);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getUserRole().toString()));

        return new DefaultOAuth2User(authorities, attributes, "id");
//        UserDetails principal = new User(claims.get("email").toString(), "", authorities);
//        return new MemberContext(member, authorities, attributes, userNameAttributeName);
    }

    private boolean isNew(String oAuthType, String oAuthId) {
        return memberRepository.findByEmail("%s_%s".formatted(oAuthType, oAuthId)).isEmpty();
    }
}