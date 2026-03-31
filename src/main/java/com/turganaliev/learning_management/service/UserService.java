package com.turganaliev.learning_management.service;

import com.turganaliev.learning_management.dto.UserRegistrationDto;
import com.turganaliev.learning_management.model.User;

public interface UserService {
    User registerUser(UserRegistrationDto userData);
    User findByUsername(String username);
}
