package com.yonvengers.smart_alcohol.register.repository;

import com.yonvengers.smart_alcohol.register.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByUserId(Long userId);
}
