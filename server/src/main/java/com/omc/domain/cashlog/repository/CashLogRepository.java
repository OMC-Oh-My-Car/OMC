package com.omc.domain.cashlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.cashlog.entity.CashLog;
import com.omc.domain.member.entity.Member;

public interface CashLogRepository extends JpaRepository<CashLog, Long> {
    List<CashLog> findAllByMember(Member member);
}
