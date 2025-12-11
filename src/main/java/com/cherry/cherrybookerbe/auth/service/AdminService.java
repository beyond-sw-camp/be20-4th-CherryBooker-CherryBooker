package com.cherry.cherrybookerbe.auth.service;

import com.cherry.cherrybookerbe.auth.dto.request.admin.AdminLoginRequest;
import com.cherry.cherrybookerbe.auth.dto.response.admin.AdminLoginResponse;
import com.cherry.cherrybookerbe.common.security.auth.RefreshTokenStore;
import com.cherry.cherrybookerbe.common.security.jwt.JwtTokenProvider;
import com.cherry.cherrybookerbe.user.command.domain.entity.User;
import com.cherry.cherrybookerbe.user.command.domain.entity.UserRole;
import com.cherry.cherrybookerbe.user.command.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenStore tokenStore;
    private final RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void testRedis() {
        try {
            redisTemplate.opsForValue().set("test-connection", "ok");
            log.info("🚀 Redis TEST 저장 성공");
        } catch (Exception e) {
            log.error("❌ Redis 저장 실패", e);
        }
    }
    public AdminLoginResponse login(AdminLoginRequest adminLoginRequest) {

        // 1) 이메일 검증
        User admin = userRepository.findByUserEmailAndUserRole((adminLoginRequest.getEmail()), UserRole.ADMIN)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없음"));

        // 2) 비밀번호 검증
        if (!passwordEncoder.matches(adminLoginRequest.getPassword(), admin.getUserPassword())) {
            throw new IllegalArgumentException("비밀번호 틀림");
        }

        // 3) userId문자열 추출
        String userId = admin.getUserId().toString();

        // 4) Access Token 생성
        String accessToken = jwtTokenProvider.createAccessToken(
                userId,
                Map.of(
                        "email", admin.getUserEmail(),
                        "role", admin.getUserRole().name()
                )
        );

        // 5) Refresh Token 생성
        String refreshToken = jwtTokenProvider.createRefreshToken(userId);

        // 6) Redis 저장
        tokenStore.save(
                String.valueOf(admin.getUserId()),
                refreshToken,
                jwtTokenProvider.getRefreshExpSeconds()
        );

        log.info("ADMIN 로그인 성공 → Redis 저장 refresh:{}, userId={}",refreshToken, admin.getUserId());

        return new AdminLoginResponse(accessToken, refreshToken);
    }

}
