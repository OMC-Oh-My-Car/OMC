package com.omc.domain.report.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.member.entity.Member;
import com.omc.domain.report.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
	Page<Report> findAllByStatus(Long filter, Pageable pageable);

	Page<Report> findAllByMember(Member findMember, Pageable pageable);

	Page<Report> findAllByMemberAndStatus(Member findMember, Long filter, Pageable pageable);
}
