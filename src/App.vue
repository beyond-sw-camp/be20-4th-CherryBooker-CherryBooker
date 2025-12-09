<script setup>
import NavigationBar from "@/components/NavigationBar.vue";
</script>

<template>
  <div>
    <NavBar v-if="!$route.meta.hideNav" />
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
