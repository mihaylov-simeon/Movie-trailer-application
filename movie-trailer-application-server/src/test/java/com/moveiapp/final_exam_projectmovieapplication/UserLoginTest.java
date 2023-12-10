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
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        LoggedUser loggedUser = mock(LoggedUser.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, loggedUser, null);

        String email = "test@example.com";
        String password = "password";
        UserLoginDTO userLoginDTO = new UserLoginDTO("", email, password);

        User mockUser = new User();
        mockUser.setEmail(email);
        mockUser.setName("Test User");
        mockUser.setPassword(passwordEncoder.encode(password));
        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        when(passwordEncoder.matches(password, mockUser.getPassword())).thenReturn(true);

        boolean loginResult = userService.login(userLoginDTO);

        assertTrue(loginResult, "Login should be successful");

        verify(loggedUser, times(1)).login(email);

        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void login_UnsuccessfulLogin() {
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        LoggedUser loggedUser = mock(LoggedUser.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, loggedUser, null);

        String email = "test@example.com";
        String password = "password";
        UserLoginDTO userLoginDTO = new UserLoginDTO("", email, password);

        when(userRepository.findByEmail(email)).thenReturn(null);

        boolean loginResult = userService.login(userLoginDTO);

        assertFalse(loginResult, "Login should be unsuccessful");

        verify(loggedUser, never()).login(email);
        verify(userRepository, never()).save(any());

    }
}
