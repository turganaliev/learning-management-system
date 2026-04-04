package com.turganaliev.learning_management.service;

import com.turganaliev.learning_management.dto.UserLoginDto;
import com.turganaliev.learning_management.dto.UserRegistrationDto;
import com.turganaliev.learning_management.model.Role;
import com.turganaliev.learning_management.model.User;
import com.turganaliev.learning_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserRegistrationDto userData) {
        if (userRepository.findByUsername(userData.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        User user = new User();
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());
        user.setUsername(userData.getUsername());
        user.setPasswordHash(passwordEncoder.encode(userData.getPassword()));
        user.setRole(Role.STUDENT);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public User loginUser(UserLoginDto loginData) {
        User user = userRepository.findByUsername(loginData.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(loginData.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password!");
        }

        return user;
    }
}
