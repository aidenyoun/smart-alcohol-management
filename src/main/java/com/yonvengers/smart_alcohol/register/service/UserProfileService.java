package com.yonvengers.smart_alcohol.register.service;

import com.yonvengers.smart_alcohol.login.model.User;
import com.yonvengers.smart_alcohol.login.repository.UserRepository;
import com.yonvengers.smart_alcohol.register.model.UserProfileRequest;
import com.yonvengers.smart_alcohol.register.model.UserProfile;
import com.yonvengers.smart_alcohol.register.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public UserProfile createUserProfile(String username, UserProfileRequest userProfileRequest) {
        User user = userRepository.findByUsername(username);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(user.getId());
        userProfile.setHeight(userProfileRequest.getHeight());
        userProfile.setGender(userProfileRequest.getGender());
        userProfile.setWeight(userProfileRequest.getWeight());
        userProfile.setBirthYear(userProfileRequest.getBirthYear());
        userProfile.setMonthlyDrinkGoal(userProfileRequest.getMonthlyDrinkGoal());

        return userProfileRepository.save(userProfile);
    }

    public UserProfile updateUserProfile(String username, UserProfileRequest userProfileRequest) {
        User user = userRepository.findByUsername(username);
        UserProfile existingProfile = userProfileRepository.findByUserId(user.getId());

        if (existingProfile != null) {
            userProfileRepository.delete(existingProfile);
        }
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(user.getId());
        userProfile.setHeight(userProfileRequest.getHeight());
        userProfile.setGender(userProfileRequest.getGender());
        userProfile.setWeight(userProfileRequest.getWeight());
        userProfile.setBirthYear(userProfileRequest.getBirthYear());
        userProfile.setMonthlyDrinkGoal(userProfileRequest.getMonthlyDrinkGoal());

        return userProfileRepository.save(userProfile);
    }
    public UserProfile getUserProfileByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userProfileRepository.findByUserId(user.getId());
    }
}
