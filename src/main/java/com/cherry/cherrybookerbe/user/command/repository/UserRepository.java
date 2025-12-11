package com.cherry.cherrybookerbe.user.command.repository;

import com.cherry.cherrybookerbe.user.command.domain.entity.User;
import com.cherry.cherrybookerbe.user.command.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserEmail(String email);
    Optional<User> findByUserId(Integer userId);
    Optional<User>  findByUserEmailAndUserRole(String email, UserRole role);
    Optional<User> findByUserIdAndUserRole(Integer userId, UserRole role);
    boolean existsByUserEmailAndUserRole(String email, UserRole role);

}


