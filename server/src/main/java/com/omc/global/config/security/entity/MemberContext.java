package com.omc.global.config.security.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.omc.domain.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@JsonIncludeProperties({"username", "email", "userRole"})
public class MemberContext extends User {
    private final String username;
    private final String email;
    private final Set<GrantedAuthority> userRole;

    public MemberContext(Member member) {
        super(member.getUsername(), "", member.getUserRole());
        username = member.getUsername();
        email = member.getEmail();
        userRole = member.getUserRole().stream().collect(Collectors.toSet());
    }
}