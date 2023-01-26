package com.omc.domain.member.dto;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class LoginDto {
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public boolean isNotValid() {
        return email == null || password == null || email.trim().length() == 0 || password.trim().length() == 0;
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
