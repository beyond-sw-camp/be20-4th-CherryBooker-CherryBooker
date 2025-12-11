// src/stores/notificationStore.js
import { defineStore } from 'pinia'
import { ref } from 'vue'
// 필요하면 나중에 실제 API 연결
// import { fetchMyUnreadCount } from '@/api/notificationApi'

export const useNotificationStore = defineStore('notification', () => {
    const unreadCount = ref(0)
    // SSE EventSource 등을 쓰고 싶으면 여기에서 관리
    // const eventSource = ref(null)

    const fetchUnreadCount = async () => {
        // TODO: 실제 알림 API와 연동
        // const data = await fetchMyUnreadCount()
        // unreadCount.value = data.unreadCount
        unreadCount.value = 0
    }

    const connectSse = () => {
        // TODO: SSE 연결 로직 구현
        // eventSource.value = new EventSource('/api/notifications/stream')
    }

    const disconnectSse = () => {
        // TODO: SSE 연결 해제 로직
        // if (eventSource.value) {
        //   eventSource.value.close()
        //   eventSource.value = null
        // }
    }

    return {
        unreadCount,
        fetchUnreadCount,
        connectSse,
        disconnectSse,
    }
})
