<script setup>
import NavigationBar from "@/components/NavigationBar.vue";
</script>

<template>
  <div>
    <!-- 네비게이션: 로그인 페이지에서는 숨김 -->
    <NavigationBar v-if="!$route.meta?.hideNav" />

    <!-- 라우터 화면 -->
    <router-view />
  </div>
</template>


<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  margin: 0;
  background-color: #FFFAEE;
}

.app-container {
  font-family: "Pretendard", sans-serif;
}
</style>
