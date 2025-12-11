<template>
  <button class="add-card" @click="$emit('click')">
    <div class="plus-circle">+</div>
    <p>책 추가</p>
  </button>
</template>

<script setup>
defineEmits(["click"]);
</script>

<style scoped>
.add-card {
  width: 150px;
  height: 210px;
  border-radius: 16px;
  border: 2px dashed #df3e3e;
  background: #fffaf4;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 12px;
  cursor: pointer;
  color: #df3e3e;
  font-weight: 600;
  font-size: 15px;
  box-shadow: inset 0 0 0 rgba(0, 0, 0, 0);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.add-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 18px rgba(223, 62, 62, 0.15);
}

.plus-circle {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  border: 2px solid #df3e3e;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 26px;
  font-weight: 600;
}

p {
  margin: 0;
}
</style>
