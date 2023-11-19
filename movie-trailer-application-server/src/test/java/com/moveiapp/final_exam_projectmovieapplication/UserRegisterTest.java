package com.moveiapp.final_exam_projectmovieapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moveiapp.final_exam_projectmovieapplication.controllers.UserController;
import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserRegistrationDTO;
import com.moveiapp.final_exam_projectmovieapplication.service.UserService;
import com.moveiapp.final_exam_projectmovieapplication.service.LoggedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserRegisterTest {

    @Mock
    private UserService userService;

    @Mock
    private LoggedUser loggedUser;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void register_Successful() throws Exception {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("Test User", "test@example.com", "password");

        when(loggedUser.isLogged()).thenReturn(false);
        when(userService.register(userRegistrationDTO)).thenReturn(true);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userRegistrationDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registration successful"));

        verify(loggedUser).isLogged();
        verify(userService).register(userRegistrationDTO);
    }

    // Helper method to convert objects to JSON string
    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
