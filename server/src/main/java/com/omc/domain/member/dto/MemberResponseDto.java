package com.omc.domain.member.dto;

import com.omc.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private String username;
    private String email;
    private String nickname;
    private String phone;

    public static <T> ResponseEntity<T> responseEntityOf(HttpHeaders headers) {
        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getUsername(), member.getNickname(), member.getPhone(), member.getEmail());
    }
}
