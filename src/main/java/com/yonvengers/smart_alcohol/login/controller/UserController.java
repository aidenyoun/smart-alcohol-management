package com.yonvengers.smart_alcohol.login.controller;

import com.yonvengers.smart_alcohol.login.model.User;
import com.yonvengers.smart_alcohol.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && userService.checkPassword(user.getPassword(), existingUser.getPassword())) {
            boolean isFirstLogin = userService.isFirstLogin(existingUser.getId());
            if (isFirstLogin) {
                return ResponseEntity.ok("Login successful, first login");
            } else {
                return ResponseEntity.ok("Login successful");
            }
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
