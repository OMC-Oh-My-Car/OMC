package com.omc.domain.cashlog.repository;

import com.omc.domain.cashlog.entity.CashLog;
import com.omc.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashLogRepository extends JpaRepository<CashLog, Long> {
    List<CashLog> findAllByMember(Member member);
}
