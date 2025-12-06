import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: "/quotes",
        name: "quotes",
        component: () => import("../views/quote/QuotesView.vue")
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
