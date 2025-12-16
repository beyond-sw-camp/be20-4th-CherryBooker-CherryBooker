package com.cherry.cherrybookerbe.notification.query.service;

import com.cherry.cherrybookerbe.common.dto.Pagination;
import com.cherry.cherrybookerbe.notification.command.domain.entity.Notification;
import com.cherry.cherrybookerbe.notification.command.domain.entity.NotificationSendLog;
import com.cherry.cherrybookerbe.notification.command.domain.entity.NotificationTemplate;
import com.cherry.cherrybookerbe.notification.command.domain.enums.NotificationSendStatus;
import com.cherry.cherrybookerbe.notification.command.domain.enums.NotificationTemplateType;
import com.cherry.cherrybookerbe.notification.command.domain.repository.NotificationRepository;
import com.cherry.cherrybookerbe.notification.command.domain.repository.NotificationSendLogRepository;
import com.cherry.cherrybookerbe.notification.command.domain.repository.NotificationTemplateRepository;
import com.cherry.cherrybookerbe.notification.command.dto.response.NotificationTemplateResponse;
import com.cherry.cherrybookerbe.notification.query.dto.response.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationQueryServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationTemplateRepository templateRepository;

    @Mock
    private NotificationSendLogRepository sendLogRepository;

    @InjectMocks
    private NotificationQueryService notificationQueryService;

    @Test
    @DisplayName("알림함 목록: 사용자는 자신의 알림 목록을 페이징으로 조회할 수 있다")
    void getMyNotifications_returnsPagedNotificationList() {
        // given
        LocalDateTime now = LocalDateTime.now();

        Notification n1 = Notification.builder().userId(1).title("알림1").content("내용1").build();
        ReflectionTestUtils.setField(n1, "id", 1);
        ReflectionTestUtils.setField(n1, "read", false);
        ReflectionTestUtils.setField(n1, "createdAt", now.minusMinutes(1));

        Notification n2 = Notification.builder().userId(1).title("알림2").content("내용2").build();
        ReflectionTestUtils.setField(n2, "id", 2);
        ReflectionTestUtils.setField(n2, "read", true);
        ReflectionTestUtils.setField(n2, "createdAt", now.minusMinutes(5));

        Page<Notification> page = new PageImpl<>(
                List.of(n1, n2),
                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt")),
                2
        );

        when(notificationRepository.findByUserIdOrderByCreatedAtDesc(eq(1), any(Pageable.class)))
                .thenReturn(page);

        // when
        NotificationPageResponse response =
                notificationQueryService.getMyNotifications(1, 0, 10);

        // then
        assertThat(response.getNotifications()).hasSize(2);
        assertThat(response.getNotifications().get(0).getNotificationId()).isEqualTo(1);
        assertThat(response.getNotifications().get(0).isRead()).isFalse();
        assertThat(response.getNotifications().get(1).getNotificationId()).isEqualTo(2);
        assertThat(response.getNotifications().get(1).isRead()).isTrue();

        Pagination pagination = response.getPagination();
        assertThat(pagination.getCurrentPage()).isEqualTo(0);
        assertThat(pagination.getTotalPages()).isEqualTo(1);
        assertThat(pagination.getTotalItems()).isEqualTo(2);

        // Pageable 검증(정렬/사이즈)
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(notificationRepository).findByUserIdOrderByCreatedAtDesc(eq(1), pageableCaptor.capture());
        Pageable used = pageableCaptor.getValue();
        assertThat(used.getPageNumber()).isEqualTo(0);
        assertThat(used.getPageSize()).isEqualTo(10);
        assertThat(used.getSort().getOrderFor("createdAt")).isNotNull();
        assertThat(used.getSort().getOrderFor("createdAt").getDirection()).isEqualTo(Sort.Direction.DESC);
    }

    @Test
    @DisplayName("알림함 미읽음 개수: 사용자는 자신의 미읽음 알림 개수를 조회할 수 있다")
    void getMyUnreadCount_returnsUnreadCount() {
        // given
        when(notificationRepository.countByUserIdAndReadFalse(1)).thenReturn(3L);

        // when
        long count = notificationQueryService.getMyUnreadCount(1);

        // then
        assertThat(count).isEqualTo(3L);
    }

    @Test
    @DisplayName("NTF-002: 관리자는 등록된 템플릿 목록을 조회할 수 있다 (키워드 없음)")
    void getTemplates_withoutKeyword_returnsTemplatePage() {
        // given
        NotificationTemplate t1 = NotificationTemplate.builder()
                .title("시스템 공지")
                .body("본문1")
                .type(NotificationTemplateType.SYSTEM)
                .build();
        ReflectionTestUtils.setField(t1, "id", 1);

        NotificationTemplate t2 = NotificationTemplate.builder()
                .title("답변 알림")
                .body("본문2")
                .type(NotificationTemplateType.EVENT_THREAD_REPLY)
                .build();
        ReflectionTestUtils.setField(t2, "id", 2);

        Page<NotificationTemplate> page = new PageImpl<>(
                List.of(t1, t2),
                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt")),
                2
        );

        when(templateRepository.findByDeletedFalse(any(Pageable.class))).thenReturn(page);

        // when
        NotificationTemplatePageResponse response =
                notificationQueryService.getTemplates(null, 0, 10);

        // then
        assertThat(response.getTemplates())
                .extracting(NotificationTemplateResponse::getTemplateId)
                .containsExactly(1, 2);

        Pagination pagination = response.getPagination();
        assertThat(pagination.getCurrentPage()).isEqualTo(0);
        assertThat(pagination.getTotalItems()).isEqualTo(2);
    }

    @Test
    @DisplayName("NTF-002: 관리자는 키워드로 템플릿 제목을 검색할 수 있다")
    void getTemplates_withKeyword_filtersByTitle() {
        // given
        NotificationTemplate t1 = NotificationTemplate.builder()
                .title("답변 알림")
                .body("본문")
                .type(NotificationTemplateType.EVENT_THREAD_REPLY)
                .build();
        ReflectionTestUtils.setField(t1, "id", 2);

        Page<NotificationTemplate> page = new PageImpl<>(
                List.of(t1),
                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt")),
                1
        );

        when(templateRepository.findByDeletedFalseAndTitleContainingIgnoreCase(eq("답변"), any(Pageable.class)))
                .thenReturn(page);

        // when
        NotificationTemplatePageResponse response =
                notificationQueryService.getTemplates("답변", 0, 10);

        // then
        assertThat(response.getTemplates()).hasSize(1);
        assertThat(response.getTemplates().get(0).getTitle()).contains("답변");
    }

    @Test
    @DisplayName("NTF-006: 관리자는 발송한 알림 로그 목록을 조회할 수 있다")
    void getSendLogs_returnsSendLogPage() {
        // given
        NotificationTemplate template = NotificationTemplate.builder()
                .title("답변 알림")
                .body("본문")
                .type(NotificationTemplateType.EVENT_THREAD_REPLY)
                .build();
        ReflectionTestUtils.setField(template, "id", 10);

        NotificationSendLog log1 = NotificationSendLog.builder()
                .template(template)
                .status(NotificationSendStatus.SUCCESS)
                .bodySnapshot("본문 스냅샷")
                .sentAt(LocalDateTime.now().minusMinutes(1))
                .build();
        ReflectionTestUtils.setField(log1, "id", 100);

        Page<NotificationSendLog> page = new PageImpl<>(
                List.of(log1),
                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "sentAt")),
                1
        );

        when(sendLogRepository.findAllByOrderBySentAtDesc(any(Pageable.class)))
                .thenReturn(page);

        // when
        NotificationSendLogPageResponse response =
                notificationQueryService.getSendLogs(0, 10);

        // then
        assertThat(response.getLogs()).hasSize(1);
        NotificationSendLogResponse item = response.getLogs().get(0);
        assertThat(item.getSendLogId()).isEqualTo(100);
        assertThat(item.getTemplateId()).isEqualTo(10);
        assertThat(item.getTemplateTitle()).isEqualTo("답변 알림");
        assertThat(item.getStatus()).isEqualTo(NotificationSendStatus.SUCCESS);

        Pagination pagination = response.getPagination();
        assertThat(pagination.getTotalItems()).isEqualTo(1);
    }
}
