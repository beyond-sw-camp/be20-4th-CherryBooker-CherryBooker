<template>
  <div class="quotes-container">

    <!-- 상단 제목 + active underline -->
    <div class="page-title">
      <span>내 글귀</span>
    </div>

    <p class="subtitle">내가 남긴 모든 글귀들을 확인해보세요!</p>

    <!-- 검색창 + 버튼 -->
    <div class="search-row">
      <div class="left-space"></div>

      <div class="center-box">
        <input
            v-model="keyword"
            type="text"
            class="search-input"
            placeholder="책 제목"
        />
        <button class="search-btn" @click="searchQuotes">검색하기</button>
      </div>

      <div class="add-btn-box">
        <button class="add-btn" @click="goToCreateQuote">추가하기</button>
      </div>

    </div>

    <!-- 글귀 카드 리스트 -->
    <div class="quotes-grid">
      <div
          class="quote-card"
          v-for="quote in filteredQuotes"
          :key="quote.quoteId"
      >
        <div class="quote-title">{{ quote.bookTitle }} – {{ quote.author }}</div>

        <div class="quote-content">
          "{{ quote.content }}"
        </div>

        <div class="detail-btn" @click="openDetail(quote)">
          +
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const keyword = ref("");

// ⭐ 백엔드에서 받아오는 내 글귀 리스트 (예시 데이터)
const quotes = ref([
  {
    quoteId: 1,
    bookTitle: "달까지 가자",
    author: "장류진",
    content: "내가 나에게 조금 더 친절해도 괜찮다는 걸, 아주 늦게야 깨달았다.",
  },
  {
    quoteId: 2,
    bookTitle: "아몬드",
    author: "손원평",
    content:
        "상처는 느끼지 못해도, 누군가의 온기는 분명히 기억된다는 걸 알게 되었다.",
  },
  {
    quoteId: 3,
    bookTitle: "해리포터와 아즈카반의 죄수",
    author: "J.K. 롤링",
    content:
        "두려움이 크다는 건, 그만큼 지키고 싶은 것이 있다는 뜻이었다.",
  },
]);

// 검색 필터
const filteredQuotes = computed(() => {
  if (!keyword.value) return quotes.value;
  return quotes.value.filter((q) =>
      q.bookTitle.toLowerCase().includes(keyword.value.toLowerCase())
  );
});

// 글귀 추가 페이지 이동
const goToCreateQuote = () => router.push("/quotes/new");

// 글귀 상세 모달/페이지 이동
const openDetail = (quote) => {
  router.push(`/quotes/${quote.quoteId}`);
};

// 검색 기능 (원하면 API 연동 가능)
const searchQuotes = () => {
  console.log("검색어:", keyword.value);
};
</script>

<style scoped>
/* 전체 레이아웃 */
.quotes-container {
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
  text-align: center;

  background: #ffffff;
  box-shadow: 0 4px 10px rgba(223, 62, 62, 0.15);

  font-family: "Pretendard", sans-serif;
}

/* 부제 */
.subtitle {
  text-align: center;
  font-size: 14px;
  color: #777;
  margin-top: 10px;
}

/* 검색창 + 버튼 */
.search-row {
  display: flex;
  align-items: center;
  margin-top: 25px;
  width: 100%;
}

.left-space {
  flex: 1;
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

/* 추가하기 버튼 */
.add-btn-box {
  flex: 1;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.add-btn {
  height: 32px;
  padding: 0 14px;
  border: none;
  border-radius: 16px;
  background: #ffa83d;
  color: white;
  cursor: pointer;
  font-size: 13px;
  white-space: nowrap;
}

/* 그리드 */
.quotes-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 18px;
  margin-top: 30px;
}

/* 카드 */
.quote-card {
  padding: 20px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.07);
  transition: 0.2s;
  cursor: pointer;
}

.quote-card:hover {
  transform: translateY(-4px);
}

.quote-title {
  font-size: 15px;
  font-weight: 600;
  margin-bottom: 10px;
  color: #333;
}

.quote-content {
  font-size: 13px;
  color: #555;
  line-height: 1.5;
  height: 70px;
  overflow: hidden;
}

.detail-btn {
  font-size: 22px;
  font-weight: bold;
  color: #ffa83d;
  text-align: center;
  margin-top: 10px;
  cursor: pointer;
}
</style>
