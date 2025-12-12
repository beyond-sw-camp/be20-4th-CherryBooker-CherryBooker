package com.cherry.cherrybookerbe.report.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ReportSummaryResponse {
    private long totalCount;
    private long pendingCount;
    private long completedCount;

    public ReportSummaryResponse(long totalCount, long pendingCount, long completedCount) {
        this.totalCount = totalCount;
        this.pendingCount = pendingCount;
        this.completedCount = completedCount;
    }
}
