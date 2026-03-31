package com.turganaliev.learning_management.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
