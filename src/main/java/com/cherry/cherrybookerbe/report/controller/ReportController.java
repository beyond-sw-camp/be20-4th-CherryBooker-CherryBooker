package com.cherry.cherrybookerbe.report.controller;

import com.cherry.cherrybookerbe.report.command.ReportCommandService;
import com.cherry.cherrybookerbe.report.command.dto.ProcessReportRequest;
import com.cherry.cherrybookerbe.report.query.dto.ReportPendingResponse;
import com.cherry.cherrybookerbe.report.query.dto.ReportSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportQueryService reportQueryService;
    private final ReportCommandService reportCommandService;

    // ✅ (1) 신고 통계 조회
    @GetMapping("/summary")
    public ReportSummaryResponse getSummary() {
        return reportQueryService.getReportSummary();
    }

    // ✅ (2) 신고 목록 조회 (관리자 테이블)
    @GetMapping
    public List<ReportPendingResponse> getReportList() {
        return reportQueryService.getPendingReportsForAdmin();
    }

    // ✅ (3) 신고 처리 (VALID / REJECTED)
    @PostMapping("/process")
    public void processReport(@RequestBody ProcessReportRequest request) throws IllegalAccessException {
        reportCommandService.process(request);
    }
    @GetMapping("/{reportId}")
    public ReportPendingResponse getReportDetail(@PathVariable Long reportId) {
        return reportQueryService.getReportDetail(reportId);
    }
}