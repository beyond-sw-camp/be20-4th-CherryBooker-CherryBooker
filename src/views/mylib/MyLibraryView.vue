<template>
  <div class="library-container">

    <!-- 상단 제목 -->
    <div class="page-title">
      <span>나의 서재</span>
    </div>

    <!-- 검색창 + 버튼 -->
    <div class="search-row">

      <div class="center-box">
        <input
            v-model="keyword"
            type="text"
            class="search-input"
            placeholder="책 제목 또는 저자"
        />
        <button class="search-btn" @click="searchBooks">검색하기</button>
      </div>

    </div>

  </div>
</template>

<script setup>
import { ref } from "vue";

const keyword = ref("");

const searchBooks = () => {
  console.log("검색어:", keyword.value);
  alert(`'${keyword.value}' 로 검색합니다`);
};

const openAddBookModal = () => {
  alert("책 추가 모달을 열겠습니다.");
};
</script>

<style scoped>
/* 전체 레이아웃 */
.library-container {
  max-width: 1100px;
  margin: 0 auto;
  padding: 30px 20px;
  font-family: "Pretendard", sans-serif;
  text-align: center;
}

/* 제목 */
.page-title {
  display: inline-block;
  margin: 20px auto 10px auto;
  padding: 14px 70px;
  border: 2px solid #df3e3e;
  border-radius: 40px;

  font-size: 20px;
  font-weight: 600;
  color: #df3e3e;
  background: #ffffff;
  box-shadow: 0 4px 10px rgba(223, 62, 62, 0.15);
}

/* 검색창 */
.search-row {
  display: flex;
  align-items: center;
  margin-top: 25px;
  width: 100%;
}

/* 가운데 검색 영역 */
.center-box {
  flex: 2;
  display: flex;
  justify-content: center;
  gap: 6px;
}

.search-input {
  width: 240px;
  height: 32px;
  border: 1px solid #ddd;
  border-radius: 16px;
  padding: 0 12px;
  background: #fafafa;
}

.search-btn {
  height: 32px;
  padding: 0 14px;
  border: none;
  border-radius: 16px;
  background: #ffd892;
  cursor: pointer;
  font-size: 13px;
}
</style>
