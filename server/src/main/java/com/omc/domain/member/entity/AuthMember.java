package com.omc.domain.member.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthMember extends Member implements UserDetails {
    private String email;
    private String password;
    private List<String> roles;
    private String nickname;

    private AuthMember(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.roles = List.of(member.getUserRole().toString());
        this.nickname = member.getUsername();
    }

    private AuthMember(String email, List<String> roles) {
        this.email = email;
        this.password = "";
        this.roles = roles;
    }

    public static AuthMember of(Member member) {
        return new AuthMember(member);
    }

    public static AuthMember of(String email, List<String> userRole) {
        return new AuthMember(email, userRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.get(0)));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
