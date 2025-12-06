package com.cherry.cherrybookerbe.quote.command.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quote")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Boolean isDeleted;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        isDeleted = false;
        createdAt = LocalDateTime.now();
    }
}
