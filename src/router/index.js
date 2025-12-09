import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: "/mypage",
        name: "mypage",
        component: () => import("../views/mypage/MyPageView.vue")
    },
    {
        path: "/mypage/profile",
        name: "myprofile",
        component: () => import("../views/mypage/MyProfileView.vue")
    },
    {
        path: "/mylibrary",
        name: "mylibrary",
        component: () => import("../views/mylib/MyLibraryView.vue")
    },
    {
        path: "/quotes",
        name: "quotes",
        component: () => import("../views/quote/QuotesView.vue")
    },
    {
        path: "/thread",
        name: "thread",
        component: () => import("@/views/thread/ThreadView.vue"),
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
