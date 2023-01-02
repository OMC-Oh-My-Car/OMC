package com.omc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
