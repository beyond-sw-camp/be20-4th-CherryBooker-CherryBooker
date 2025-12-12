package com.cherry.cherrybookerbe.report.command.dto;

import com.cherry.cherrybookerbe.report.domain.ReportStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

// 관리자 판단 dto. 관리자가valid, reject할지 선택
@Getter
public class ProcessReportRequest {
    @NotNull(message = "reportId는 필수입니다.")
    private Long reportId; // 처리할 신고 1건

    @NotNull(message = "status는 필수입니다.")
    private ReportStatus status; // valid, rejected

    @Size(max = 500, message = "관리자 코멘트는 500자 이내입니다.")
    private String adminComment;

    public ProcessReportRequest() {}

}
