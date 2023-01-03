package com.omc.domain.member.dto;

import com.omc.domain.member.entity.Member;
import com.omc.domain.member.entity.Social;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPostDto {
    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Email
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "별명을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phone;

    private String profileImg;

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .nickname(nickname)
                .phone(phone)
                .profileImg("https://picsum.photos/200/300")
                .build();
    }
}
