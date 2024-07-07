package com.yonvengers.smart_alcohol.register.controller;

import com.yonvengers.smart_alcohol.register.model.UserProfile;
import com.yonvengers.smart_alcohol.register.service.UserProfileService;
import com.yonvengers.smart_alcohol.register.model.UserProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/create")
    public ResponseEntity<?> createUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        // 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserProfile userProfile = userProfileService.createUserProfile(username, userProfileRequest);
        return ResponseEntity.ok(userProfile);
    }

    @PostMapping("/recreate")
    public ResponseEntity<?> recreateUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserProfile userProfile = userProfileService.updateUserProfile(username, userProfileRequest);
        return ResponseEntity.ok(userProfile);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserProfile userProfile = userProfileService.getUserProfileByUsername(username);
        return ResponseEntity.ok(userProfile);
    }

}
