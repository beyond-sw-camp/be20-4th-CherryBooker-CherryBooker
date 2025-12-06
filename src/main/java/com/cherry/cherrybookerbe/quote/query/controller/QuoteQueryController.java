package com.cherry.cherrybookerbe.quote.query.controller;

import com.cherry.cherrybookerbe.quote.query.dto.QuoteDetailResponse;
import com.cherry.cherrybookerbe.quote.query.dto.QuoteListResponse;
import com.cherry.cherrybookerbe.quote.query.service.QuoteQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotes")
public class QuoteQueryController {

    private final QuoteQueryService quoteQueryService;

    // 글귀 상세 조회
    @GetMapping("/{quoteId}")
    public ResponseEntity<QuoteDetailResponse> getQuoteDetail(@PathVariable Long quoteId) {
        QuoteDetailResponse response = quoteQueryService.getQuoteDetail(quoteId);
        return ResponseEntity.ok(response);
    }

    // 특정 사용자의 글귀 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuoteListResponse>> getUserQuotes(@PathVariable Long userId) {
        List<QuoteListResponse> list = quoteQueryService.getQuotesByUser(userId);
        return ResponseEntity.ok(list);
    }
}
