package com.cherry.cherrybookerbe.report.command.dto;

import com.cherry.cherrybookerbe.report.domain.ReportStatus;
import lombok.Getter;

// 관리자 판단 dto. 관리자가valid, reject할지 선택
@Getter
public class ProcessReportRequest {
    private Long targetUserId;
    private ReportStatus status;

    public ProcessReportRequest() {}

}
