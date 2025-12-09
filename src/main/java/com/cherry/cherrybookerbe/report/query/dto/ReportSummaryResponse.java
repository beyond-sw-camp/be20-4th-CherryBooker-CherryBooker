package com.cherry.cherrybookerbe.report.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 신고 통계 DTO
public class ReportSummaryResponse {
    private long totalCount;     // 전체 신고 수
    private long pendingCount;   // 미처리 (PENDING)
    private long completedCount; // 처리 완료 (VALID + REJECTED)
}
