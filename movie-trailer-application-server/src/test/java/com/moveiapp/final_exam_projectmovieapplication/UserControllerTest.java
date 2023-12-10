package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.controllers.UserController;
import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserRegistrationDTO;
import com.moveiapp.final_exam_projectmovieapplication.service.UserService;
import com.moveiapp.final_exam_projectmovieapplication.service.LoggedUser;
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
        MockitoAnnotations.openMocks(this);

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("TestUser", "test@example.com", "password");

        when(loggedUser.isLogged()).thenReturn(false);
        when(userService.register(userRegistrationDTO)).thenReturn(true);

        ResponseEntity<?> response = userController.register(userRegistrationDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registration successful", response.getBody());

        verify(loggedUser).isLogged();
        verify(userService).register(userRegistrationDTO);
        verifyNoMoreInteractions(userService, loggedUser);
    }

    @Test
    void testRegisterFailure() {
        MockitoAnnotations.openMocks(this);

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("TestUser", "test@example.com", "password");

        when(loggedUser.isLogged()).thenReturn(false);
        when(userService.register(userRegistrationDTO)).thenReturn(false);

        ResponseEntity<?> response = userController.register(userRegistrationDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Registration failed", response.getBody());

        verify(loggedUser).isLogged();
        verify(userService).register(userRegistrationDTO);
        verifyNoMoreInteractions(userService, loggedUser);
    }

    @Test
    void testRegisterUserAlreadyLoggedIn() {
        MockitoAnnotations.openMocks(this);

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("TestUser", "test@example.com", "password");

        when(loggedUser.isLogged()).thenReturn(true);

        ResponseEntity<?> response = userController.register(userRegistrationDTO);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("User is already logged in.", response.getBody());

        verify(loggedUser).isLogged();
        verifyNoMoreInteractions(userService, loggedUser);
    }
}
