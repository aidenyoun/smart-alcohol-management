package com.yonvengers.smart_alcohol.login.service;

import com.yonvengers.smart_alcohol.login.model.User;
import com.yonvengers.smart_alcohol.login.repository.UserRepository;
import com.yonvengers.smart_alcohol.register.model.UserProfile;
import com.yonvengers.smart_alcohol.register.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserProfileRepository userProfileRepository; // UserProfileRepository 인스턴스 추가

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public boolean isFirstLogin(Long userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId);
        return userProfile == null;
    }
}
