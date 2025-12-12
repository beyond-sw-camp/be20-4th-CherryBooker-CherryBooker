package com.cherry.cherrybookerbe.report.query;

import com.cherry.cherrybookerbe.report.domain.Report;
import com.cherry.cherrybookerbe.report.domain.ReportStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 5회 이상 신고 받은 글 목록(pending상태) 조회
public interface ReportQueryRepository extends JpaRepository<Report, Long> {

    @Query(value = """
                    SELECT DISTINCT r.threads_id
                    FROM report r
                    JOIN threads t ON r.threads_id = t.threads_id
                    WHERE r.status = 'PENDING'
                    AND t.report_count >= 5
            """, nativeQuery = true)
    List<Long> findPendingThreadIdsReportedOverFive();

    // 통계
    // 전체 신고 수
    long count();

    // 미처리 신고 수 (PENDING)
    long countByStatus(ReportStatus status);

    // 처리 완료 수 (VALID + REJECTED)
    long countByStatusIn(List<ReportStatus> statusList);
}
