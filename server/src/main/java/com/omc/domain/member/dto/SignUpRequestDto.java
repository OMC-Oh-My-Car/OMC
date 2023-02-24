package com.omc.domain.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.omc.domain.img.entity.MemberImg;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.omc.domain.member.entity.Member;
import com.omc.domain.member.entity.Social;
import com.omc.domain.member.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    public Member signUp(List<MemberImg> memberImgs) {
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .nickname(nickname)
                .phone(phone)
                .memberImgList(memberImgs)
                .isSocial(Social.ORIGIN)
                .userRole(UserRole.ROLE_ADMIN)
                .build();
    }

    public Member encodePasswordSignUp(PasswordEncoder passwordEncoder, List<MemberImg> memberImgs) {
        return Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .phone(phone)
                .memberImgList(memberImgs)
                .isSocial(Social.ORIGIN)
                .userRole(UserRole.ROLE_USER)
                .build();
    }

    public Member encodePasswordSellerSignUp(PasswordEncoder passwordEncoder, List<MemberImg> memberImgs) {
        return Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .phone(phone)
                .memberImgList(memberImgs)
                .isSocial(Social.ORIGIN)
                .userRole(UserRole.ROLE_SELLER)
                .build();
    }
}
