package com.turganaliev.learning_management.controller;

import com.turganaliev.learning_management.dto.UserLoginDto;
import com.turganaliev.learning_management.dto.UserRegistrationDto;
import com.turganaliev.learning_management.model.User;
import com.turganaliev.learning_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegistrationDto userData) {
        User newUser = userService.registerUser(userData);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto loginData) {
        User user = userService.loginUser(loginData);
        return ResponseEntity.ok(user);
    }
}
