package com.omc.domain.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.omc.domain.member.entity.Member;
import com.omc.domain.member.entity.Social;
import com.omc.domain.member.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequestDto {
    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String passwordConfirm;

    @Email(message = "이메일 형식으로 작성해주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "별명을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phone;

    private String profileImg;

    public Member signUp() {
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .nickname(nickname)
                .phone(phone)
                .profileImg("https://picsum.photos/200/300")
                .isSocial(Social.ORIGIN)
                .userRole(UserRole.ROLE_ADMIN)
                .build();
    }

    public Member encodePasswordSignUp(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .phone(phone)
                .profileImg("https://picsum.photos/200/300")
                .isSocial(Social.ORIGIN)
                .userRole(UserRole.ROLE_USER)
                .build();
    }

    public Member encodePasswordSellerSignUp(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .phone(phone)
                .profileImg("https://picsum.photos/200/300")
                .isSocial(Social.ORIGIN)
                .userRole(UserRole.ROLE_SELLER)
                .build();
    }
}
