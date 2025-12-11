// src/api/userApi.js
import apiClient from '@/api/httpClient'

/**
 * 내 정보 조회
 * GET /api/users/me
 * 응답: ApiResponse<UserDto>
 *  - data: { userId, userEmail, userName, userNickname, userRole, ... }
 */
export async function fetchMyInfoApi() {
    const res = await apiClient.get('/users/me')
    return res.data.data
}

/**
 * 알림 서비스나 공통 인증용 /auth/me
 * (현재 authStore.ensureUserId 에서 사용)
 *
 * GET /api/auth/me
 * 응답: ApiResponse<AuthMeDto>
 *  - data: { userId, username, role, ... } 형식이라고 가정
 */
export async function fetchNotificationAuthMe() {
    const res = await apiClient.get('/auth/me')
    return res.data.data
}
