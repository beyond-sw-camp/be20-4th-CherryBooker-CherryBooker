<script setup>
import NavigationBar from "@/components/NavigationBar.vue";
</script>

<template>
  <div class="app-container">
    <!-- 공통 네비게이션 -->
    <NavigationBar />

    <!-- 페이지가 바뀌는 영역 -->
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
