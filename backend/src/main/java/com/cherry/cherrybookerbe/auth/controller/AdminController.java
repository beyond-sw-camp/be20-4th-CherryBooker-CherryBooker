package com.cherry.cherrybookerbe.auth.controller;

import com.cherry.cherrybookerbe.auth.dto.request.admin.AdminLoginRequest;
import com.cherry.cherrybookerbe.auth.dto.response.admin.AdminLoginResponse;
import com.cherry.cherrybookerbe.auth.service.AdminService;
import com.cherry.cherrybookerbe.common.dto.ApiResponse;
import com.cherry.cherrybookerbe.common.security.auth.RefreshTokenStore;
import com.cherry.cherrybookerbe.common.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminAuthService;
    private final RefreshTokenStore tokenStore;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login (@RequestBody AdminLoginRequest adminLoginRequest){
        AdminLoginResponse loginResponse = adminAuthService.login(adminLoginRequest);
        return ResponseEntity.ok(ApiResponse.success(loginResponse));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(@AuthenticationPrincipal UserPrincipal principal) {

        tokenStore.delete(principal.userId().toString());
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}

