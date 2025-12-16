package com.cherry.cherrybookerbe.report.controller;


import com.cherry.cherrybookerbe.common.dto.ApiResponse;
import com.cherry.cherrybookerbe.report.command.ReportCommandService;
import com.cherry.cherrybookerbe.report.command.dto.CreateReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportUserController {

    private final ReportCommandService reportCommandService;

    //게시글 신고 (일반 사용자)
    @PostMapping
    public ApiResponse<Void> createReport(
            @RequestBody CreateReportRequest request
    ) {
        reportCommandService.create(request);
        return ApiResponse.success(null);
    }
}
