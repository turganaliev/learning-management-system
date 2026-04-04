package com.turganaliev.learning_management;

import com.turganaliev.learning_management.dto.UserLoginDto;
import com.turganaliev.learning_management.model.User;
import com.turganaliev.learning_management.repository.UserRepository;
import com.turganaliev.learning_management.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void loginUser_Success() {
        UserLoginDto loginDto = new UserLoginDto();
        loginDto.setUsername("john1234");
        loginDto.setPassword("password1234");

        User fakeUser = new User();
        fakeUser.setUsername("john1234");
        fakeUser.setPasswordHash("hashedPassword");

        when(userRepository.findByUsername("john1234")).thenReturn(Optional.of(fakeUser));
        when(passwordEncoder.matches("password1234", "hashedPassword")).thenReturn(true);

        User result = userService.loginUser(loginDto);
        assertNotNull(result);
        assertEquals("john1234", result.getUsername());
    }

    @Test
    void loginUser_WrongPassword() {
        UserLoginDto loginDto = new UserLoginDto();
        loginDto.setUsername("bill123");
        loginDto.setPassword("wrongpassword");

        User fakeUser = new User();
        fakeUser.setUsername("bill123");
        fakeUser.setPasswordHash("hashedPassword");

        when(userRepository.findByUsername("bill123")).thenReturn(Optional.of(fakeUser));
        when(passwordEncoder.matches("wrongpassword", "hashedPassword")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {userService.loginUser(loginDto);});
    }
}
