package com.moveiapp.final_exam_projectmovieapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moveiapp.final_exam_projectmovieapplication.controllers.UserController;
import com.moveiapp.final_exam_projectmovieapplication.service.UserService;
import com.moveiapp.final_exam_projectmovieapplication.service.LoggedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserLogoutTest {

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
    void logout_Successful() throws Exception {
        when(loggedUser.isLogged()).thenReturn(true);

        mockMvc.perform(post("/logout"))
                .andExpect(status().isOk())
                .andExpect(content().string("Logout successful"));

        verify(loggedUser).isLogged();
        verify(userService).logout(loggedUser.getEmail());
        verify(loggedUser).logout();
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
