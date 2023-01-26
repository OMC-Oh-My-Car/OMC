package com.omc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.member.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);

    @Transactional
    void deleteByEmail(String email);

    Optional<Member> findByPhone(String param);
}
