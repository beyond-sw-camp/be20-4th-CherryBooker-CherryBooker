package com.cherry.cherrybookerbe.report.command.dto;

import lombok.Getter;

// 신고 등록 ; 신고 1건 생성
@Getter
public class CreateReportRequest {
    private Long targetUserId; // 신고받은 유저 id fk
    private Long threadId; // 스레드 id fk
    private Long threadsReplyId; // 스레드 답글 id fk

    public CreateReportRequest() {}
}
