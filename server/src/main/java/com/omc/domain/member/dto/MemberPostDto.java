package com.omc.domain.member.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPostDto {

    private String username;
    private String password;
    private String email;
    private String nickname;
    private String phone;
}
