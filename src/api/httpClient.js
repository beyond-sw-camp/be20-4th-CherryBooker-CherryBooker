// src/api/httpClient.js
import axios from 'axios'
import { useAuthStore } from '@/stores/authStore'

const apiClient = axios.create({
    baseURL: '/api',
    withCredentials: true,
})

/**
 * 요청 인터셉터
 * - accessToken 없으면 localStorage에서 복원
 * - 있으면 Authorization 헤더에 Bearer 토큰 자동 세팅
 */
apiClient.interceptors.request.use(
    (config) => {
        const authStore = useAuthStore()

        if (!authStore.accessToken) {
            authStore.loadFromStorage()
        }

        const token = authStore.accessToken
        if (token) {
            config.headers = config.headers || {}
            config.headers.Authorization = `Bearer ${token}`
        }

        return config
    },
    (error) => Promise.reject(error),
)

export default apiClient
