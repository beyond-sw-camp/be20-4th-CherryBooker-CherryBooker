<template>
  <div class="library-container">

    <!-- 상단 제목 -->
    <div class="page-title">
      <span>내 프로필</span>
    </div>

    <!-- 프로필 카드 -->
    <div class="profile-card">

      <!-- 프로필 이미지 -->
      <div class="profile-img-box">
        <img src="/images/character1.png" class="profile-img" />
      </div>

      <!-- 정보 폼 -->
      <div class="info-row">
        <span class="label">닉네임</span>
        <div class="input-box">
          <input type="text" v-model="nickname" class="input" />
          <button class="edit-btn">수정</button>
        </div>
      </div>

      <div class="info-row">
        <span class="label">이메일</span>
        <div class="input-box">
          <input type="text" v-model="email" class="input" disabled />
        </div>
      </div>

      <div class="info-row">
        <span class="label">가입일</span>
        <div class="input-box">
          <input type="text" v-model="joinDate" class="input" disabled />
        </div>
      </div>

      <!-- 버튼 2개 -->
      <div class="btn-row">
        <button class="delete-btn">회원탈퇴</button>
        <button class="logout-btn">로그아웃</button>
      </div>

    </div>

  </div>
</template>

<script setup>
import { ref } from "vue";

const nickname = ref("김다독");
const email = ref("example@gmail.com");
const joinDate = ref("2025.12.03");
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

/* 프로필 카드 전체 */
.profile-card {
  margin: 30px auto;
  width: 500px;
  padding: 30px;
  background: #fff7e8;
  border-radius: 24px;
  border: 3px solid #c38a52;
  box-shadow: 0 8px 15px rgba(0,0,0,0.08);
}

/* 프로필 이미지 */
.profile-img-box {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.profile-img {
  width: 140px;
  height: 140px;
  background: #f7d37a;
  border-radius: 50%;
  padding: 20px;
}

/* 폼 라인 */
.info-row {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 15px;
  gap: 10px;
}

.label {
  width: 70px;
  text-align: right;
  font-size: 15px;
  font-weight: 500;
}

/* 입력 박스 */
.input-box {
  display: flex;
  align-items: center;
  gap: 8px;
}

.input {
  width: 260px;
  height: 34px;
  border: 1px solid #f2c46c;
  border-radius: 18px;
  padding: 0 12px;
  background: #fffdf7;
  font-size: 14px;
}

/* 수정 버튼 */
.edit-btn {
  background: #ffd37c;
  border: none;
  padding: 6px 14px;
  border-radius: 15px;
  font-size: 13px;
  cursor: pointer;
}

/* 버튼 2개 */
.btn-row {
  display: flex;
  justify-content: center;
  gap: 40px;
  margin-top: 20px;
}

.delete-btn {
  background: #ff9aa3;
  border: none;
  padding: 10px 24px;
  color: white;
  border-radius: 20px;
  cursor: pointer;
  font-size: 14px;
}

.logout-btn {
  background: #fcd487;
  border: none;
  padding: 10px 24px;
  color: #444;
  border-radius: 20px;
  cursor: pointer;
  font-size: 14px;
}
</style>
