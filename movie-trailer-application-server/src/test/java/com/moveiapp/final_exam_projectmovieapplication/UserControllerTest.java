package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.controllers.UserController;
import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserRegistrationDTO;
import com.moveiapp.final_exam_projectmovieapplication.service.UserService;
import com.moveiapp.final_exam_projectmovieapplication.service.impl.LoggedUser;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private LoggedUser loggedUser;

    @InjectMocks
    private UserController userController;

    @Test
    void testRegisterSuccess() {
        // Arrange
        MockitoAnnotations.openMocks(this);

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("TestUser", "test@example.com", "password");

        when(loggedUser.isLogged()).thenReturn(false);
        when(userService.register(userRegistrationDTO)).thenReturn(true);

        // Act
        ResponseEntity<?> response = userController.register(userRegistrationDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registration successful", response.getBody());

        // Verify that register method and other methods were called
        verify(loggedUser).isLogged();
        verify(userService).register(userRegistrationDTO);
        verifyNoMoreInteractions(userService, loggedUser);
    }

    @Test
    void testRegisterFailure() {
        // Arrange
        MockitoAnnotations.openMocks(this);

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("TestUser", "test@example.com", "password");

        when(loggedUser.isLogged()).thenReturn(false);
        when(userService.register(userRegistrationDTO)).thenReturn(false);

        // Act
        ResponseEntity<?> response = userController.register(userRegistrationDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Registration failed", response.getBody());

        // Verify that register method and other methods were called
        verify(loggedUser).isLogged();
        verify(userService).register(userRegistrationDTO);
        verifyNoMoreInteractions(userService, loggedUser);
    }

    @Test
    void testRegisterUserAlreadyLoggedIn() {
        // Arrange
        MockitoAnnotations.openMocks(this);

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("TestUser", "test@example.com", "password");

        when(loggedUser.isLogged()).thenReturn(true);

        // Act
        ResponseEntity<?> response = userController.register(userRegistrationDTO);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("User is already logged in.", response.getBody());

        // Verify that isLogged method was called
        verify(loggedUser).isLogged();
        verifyNoMoreInteractions(userService, loggedUser);
    }
}
