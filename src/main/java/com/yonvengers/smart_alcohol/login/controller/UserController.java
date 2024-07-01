package com.yonvengers.smart_alcohol.login.controller;

import com.yonvengers.smart_alcohol.login.model.User;
import com.yonvengers.smart_alcohol.login.service.UserService;
import com.yonvengers.smart_alcohol.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && userService.checkPassword(user.getPassword(), existingUser.getPassword())) {
            boolean isFirstLogin = userService.isFirstLogin(existingUser.getId());

            // JWT 토큰 생성
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            if (isFirstLogin) {
                return ResponseEntity.ok(new JwtResponse(jwt, "Login successful, first login"));
            } else {
                return ResponseEntity.ok(new JwtResponse(jwt, "Login successful"));
            }
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    static class JwtResponse {
        private final String jwt;
        private final String message;

        public JwtResponse(String jwt, String message) {
            this.jwt = jwt;
            this.message = message;
        }

        public String getJwt() {
            return jwt;
        }

        public String getMessage() {
            return message;
        }
    }
}
