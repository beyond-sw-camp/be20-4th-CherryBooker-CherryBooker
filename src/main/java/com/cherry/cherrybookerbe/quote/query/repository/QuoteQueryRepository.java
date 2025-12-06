package com.cherry.cherrybookerbe.quote.query.repository;

import com.cherry.cherrybookerbe.quote.command.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteQueryRepository extends JpaRepository<Quote, Long> {

    // 특정 사용자의 삭제 안된 글귀만 조회
    List<Quote> findByUserIdAndIsDeletedFalseOrderByCreatedAtDesc(Long userId);
}
