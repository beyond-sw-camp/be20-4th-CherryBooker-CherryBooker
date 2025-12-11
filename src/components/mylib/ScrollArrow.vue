<template>
  <button class="scroll-arrow" :class="direction" @click="$emit('click')">
    <span v-if="direction === 'down'">⌄</span>
    <span v-else>⌃</span>
  </button>
</template>

<script setup>
defineProps({
  direction: {
    type: String,
    default: "down",
    validator: (value) => ["down", "up"].includes(value),
  },
});

defineEmits(["click"]);
</script>

<style scoped>
.scroll-arrow {
  position: fixed;
  bottom: 32px;
  left: 50%;
  transform: translateX(-50%);
  width: 48px;
  height: 48px;
  border-radius: 50%;
  border: none;
  background: #1c1c1c;
  color: #fff;
  font-size: 26px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.25);
  z-index: 1500;
}

.scroll-arrow.up span {
  transform: translateY(-2px);
}

.scroll-arrow.down span {
  transform: translateY(2px);
}
</style>
