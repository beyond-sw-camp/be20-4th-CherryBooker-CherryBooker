package com.cherry.cherrybookerbe.auth.service;

import com.cherry.cherrybookerbe.user.command.domain.entity.User;
import com.cherry.cherrybookerbe.user.command.domain.entity.UserRole;
import com.cherry.cherrybookerbe.user.command.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User admin = userRepository.findByUserIdAndUserRole(Integer.parseInt(userId), UserRole.ADMIN)
                .orElseThrow(() -> new UsernameNotFoundException("관리자 찾을 수 없음"));
        return new org.springframework.security.core.userdetails.User(
                admin.getUserEmail(),
                admin.getUserPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

    }
}

