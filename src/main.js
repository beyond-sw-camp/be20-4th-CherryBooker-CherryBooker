import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import {createPinia} from "pinia";

const app = createApp(App);
const pinia = createPinia()
app.use(pinia)

app.use(router);
app.mount("#app");

axios.interceptors.request.use(config => {
    const token = localStorage.getItem("accessToken");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});