package com.cherry.cherrybookerbe.quote.query.service;

import com.cherry.cherrybookerbe.quote.command.entity.Quote;
import com.cherry.cherrybookerbe.quote.query.dto.QuoteDetailResponse;
import com.cherry.cherrybookerbe.quote.query.dto.QuoteListResponse;
import com.cherry.cherrybookerbe.quote.query.repository.QuoteQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteQueryService {

    private final QuoteQueryRepository repository;

    // 글귀 상세 조회
    public QuoteDetailResponse getQuoteDetail(Long quoteId) {
        Quote quote = repository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));

        return new QuoteDetailResponse(
                quote.getQuoteId(),
                quote.getContent(),
                quote.getBookTitle(),
                quote.getAuthor(),
                quote.getComment(),
                quote.getImagePath(),
                quote.getCreatedAt().toString()
        );
    }

    //특정 사용자 글귀 목록 조회
    public List<QuoteListResponse> getQuotesByUser(Long userId) {

        List<Quote> quotes = repository.findByUserIdAndIsDeletedFalseOrderByCreatedAtDesc(userId);

        return quotes.stream()
                .map(q -> new QuoteListResponse(
                        q.getQuoteId(),
                        q.getContent(),
                        q.getBookTitle(),
                        q.getAuthor(),
                        q.getCreatedAt().toString()
                ))
                .collect(Collectors.toList());
    }
}
