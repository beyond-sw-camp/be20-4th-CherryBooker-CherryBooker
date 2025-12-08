package com.cherry.cherrybookerbe.report.command;

import com.cherry.cherrybookerbe.community.command.domain.entity.CommunityReply;
import com.cherry.cherrybookerbe.community.command.domain.entity.CommunityThread;
import com.cherry.cherrybookerbe.report.command.dto.CreateReportRequest;
import com.cherry.cherrybookerbe.report.command.dto.ProcessReportRequest;
import com.cherry.cherrybookerbe.report.domain.Report;
import com.cherry.cherrybookerbe.report.query.ReportQueryRepository;
import com.cherry.cherrybookerbe.user.command.domain.entity.User;

public class ReportCommandService {
    // 신고 저장
    // 5회 이상 => 쿼리 서비스 부르기
    // 관리자 reject 처리 시 → thread에서 삭제 처리
    // 삭제되면 → user에서 정리 처리
    private final ReportCommandRepository reportCommandRepository;
    private final ReportQueryRepository reportQueryRepository;
    // user, thread 레포지토리 선언 필요
    public ReportCommandService(
            ReportCommandRepository reportCommandRepository,
            ReportQueryRepository reportQueryRepository)
    {
        this.reportCommandRepository = reportCommandRepository;
        this.reportQueryRepository = reportQueryRepository;
    }

    // 신고 등록
    public void create(CreateReportRequest createReportRequest) {
        Long targetUserId =  createReportRequest.getTargetUserId();
        Long threadId =  createReportRequest.getThreadId();
        Long threadReplyId = createReportRequest.getThreadsReplyId();
        // user, thread 리포지토리 미생성에 따른 주석 처리
        /*User targetUser = userRepository.findById(targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("신고 대상 유저 없음"));
        CommunityThread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new IllegalArgumentException("신고 대상 게시글 없음"));

        CommunityReply reply = null;
        if (threadReplyId != null) {
            reply = CommunityReplyRepository.findById(threadReplyId)
                    .orElseThrow(() -> new IllegalArgumentException("신고 대상 댓글 없음"));
        } */
        Report report = Report.create(
                targetUserId,
                threadId,
                threadReplyId
        );
        reportCommandRepository.save(report);
    }
    public void process(ProcessReportRequest processReportRequest) {
        // 관리자 판단

    }
}
