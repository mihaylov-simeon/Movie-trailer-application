package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserLoginDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.User;
import com.moveiapp.final_exam_projectmovieapplication.repositories.UserRepository;
import com.moveiapp.final_exam_projectmovieapplication.service.LoggedUser;
import com.moveiapp.final_exam_projectmovieapplication.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserLoginTest {

    @Test
    void login_SuccessfulLogin() {
        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        LoggedUser loggedUser = mock(LoggedUser.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, loggedUser, null);

        String email = "test@example.com";
        String password = "password";
        UserLoginDTO userLoginDTO = new UserLoginDTO("", email, password);

        // Mock the behavior of userRepository.findByEmail to return a user with matching credentials
        User mockUser = new User();
        mockUser.setEmail(email);
        mockUser.setName("Test User");
        mockUser.setPassword(passwordEncoder.encode(password));
        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        // Mock the behavior of passwordEncoder.matches to return true
        when(passwordEncoder.matches(password, mockUser.getPassword())).thenReturn(true);

        // Act
        boolean loginResult = userService.login(userLoginDTO);

        // Assert
        assertTrue(loginResult, "Login should be successful");

        // Verify that loggedUser.login was called once with the correct email
        verify(loggedUser, times(1)).login(email);

        // Verify that userRepository.save was called once with the updated user
        verify(userRepository, times(1)).save(mockUser);

        // You can add more assertions based on your specific requirements
    }

    @Test
    void login_UnsuccessfulLogin() {
        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        LoggedUser loggedUser = mock(LoggedUser.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, loggedUser, null);

        String email = "test@example.com";
        String password = "password";
        UserLoginDTO userLoginDTO = new UserLoginDTO("", email, password);

        // Mock the behavior of userRepository.findByEmail to return null (no user with matching email)
        when(userRepository.findByEmail(email)).thenReturn(null);

        // Act
        boolean loginResult = userService.login(userLoginDTO);

        // Assert
        assertFalse(loginResult, "Login should be unsuccessful");

        // Verify that loggedUser.login and userRepository.save were not called
        verify(loggedUser, never()).login(email);
        verify(userRepository, never()).save(any());

        // You can add more assertions based on your specific requirements
    }
}
