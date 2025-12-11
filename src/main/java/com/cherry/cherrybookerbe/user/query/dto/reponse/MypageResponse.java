package com.cherry.cherrybookerbe.user.query.dto.reponse;

import com.cherry.cherrybookerbe.user.command.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MypageResponse {

    private String email;
    private String name;
    private String nickname;
    private LocalDateTime createdAt;

    @Builder
    private MypageResponse(String email, String name,  String nickname,LocalDateTime createdAt) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.createdAt = createdAt;

    }

    public static MypageResponse from(User user) {
        return MypageResponse.builder()
                .email(user.getUserEmail())
                .name(user.getUserName())
                .nickname(user.getUserNickname())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
