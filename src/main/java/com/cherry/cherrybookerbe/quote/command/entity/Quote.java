package com.cherry.cherrybookerbe.quote.command.entity;

import com.cherry.cherrybookerbe.common.enums.Status;
import com.cherry.cherrybookerbe.common.model.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "quote")
public class Quote extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id")
    private Long quoteId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long userBookId;

    @Column(nullable = false, length = 500)
    private String content;

    private String bookTitle;
    private String author;

    private String imagePath;

    @Column(length = 500)
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.Y;

    @Builder
    private Quote(Long userId,
                  Long userBookId,
                  String content,
                  String bookTitle,
                  String author,
                  String imagePath,
                  String comment) {

        this.userId = userId;
        this.userBookId = userBookId;
        this.content = content;
        this.bookTitle = bookTitle;
        this.author = author;
        this.imagePath = imagePath;
        this.comment = comment;
    }

    // 글귀 내용 수정
    public void updateContent(String newContent) {
        this.content = newContent;
    }

    // 코멘트 수정
    public void updateComment(String newComment) {
        this.comment = newComment;
    }

    // 코멘트 삭제
    public void removeComment() {
        this.comment = null;
    }

    // 소프트 삭제
    public void delete() {
        this.status = Status.N;
    }
}
