package com.yonvengers.smart_alcohol.register.controller;

import com.yonvengers.smart_alcohol.register.model.UserProfile;
import com.yonvengers.smart_alcohol.register.model.UserProfileRequest;
import com.yonvengers.smart_alcohol.register.service.UserProfileService;
import com.yonvengers.smart_alcohol.login.model.User;
import com.yonvengers.smart_alcohol.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createProfile(@RequestBody UserProfileRequest userProfileRequest) {
        User user = userService.findByUsername(userProfileRequest.getUsername());
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        UserProfile existingProfile = userProfileService.findByUserId(user.getId());
        if (existingProfile != null) {
            return ResponseEntity.status(400).body("Profile already exists");
        }

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(user.getId());
        userProfile.setGender(userProfileRequest.getGender());
        userProfile.setBirthYear(userProfileRequest.getBirthYear());
        userProfile.setHeight(userProfileRequest.getHeight());
        userProfile.setWeight(userProfileRequest.getWeight());
        userProfile.setMonthlyDrinkGoal(userProfileRequest.getMonthlyDrinkGoal());

        userProfileService.save(userProfile);
        return ResponseEntity.ok("Profile created successfully");
    }
}
