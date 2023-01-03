package com.omc.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.report.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
