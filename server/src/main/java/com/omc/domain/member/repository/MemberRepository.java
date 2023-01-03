package com.omc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
