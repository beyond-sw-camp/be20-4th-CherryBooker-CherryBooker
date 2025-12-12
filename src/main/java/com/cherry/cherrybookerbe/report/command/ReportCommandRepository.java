package com.cherry.cherrybookerbe.report.command;

import com.cherry.cherrybookerbe.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

// 신고 저장
public interface ReportCommandRepository
        extends JpaRepository<Report, Long> {
}
