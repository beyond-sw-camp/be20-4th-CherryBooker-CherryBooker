// src/api/authApi.js
import apiClient from '@/api/httpClient'

/**
 * ID/PW 로그인
 * POST /auth/login
 * body: { userLoginId, password }
 */
export const loginApi = (userLoginId, password) =>
    apiClient.post('/auth/login', { userLoginId, password })

/**
 * 토큰 재발급
 * POST /auth/refresh
 */
export const refreshApi = () =>
    apiClient.post('/auth/refresh')

/**
 * 로그아웃
 * POST /auth/logout
 */
export const logoutApi = () =>
    apiClient.post('/auth/logout')
