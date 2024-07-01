package com.yonvengers.smart_alcohol.register.service;

import com.yonvengers.smart_alcohol.register.model.UserProfile;
import com.yonvengers.smart_alcohol.register.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile findByUserId(Long userId) {
        return userProfileRepository.findByUserId(userId);
    }

    public void save(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }
}
