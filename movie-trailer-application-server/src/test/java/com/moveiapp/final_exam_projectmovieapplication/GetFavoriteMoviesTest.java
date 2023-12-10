package com.moveiapp.final_exam_projectmovieapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moveiapp.final_exam_projectmovieapplication.controllers.UserController;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.FavoriteMovie;
import com.moveiapp.final_exam_projectmovieapplication.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GetFavoriteMoviesTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getFavoriteMovies_Successful() throws Exception {
        List<FavoriteMovie> favoriteMovies = Collections.singletonList(new FavoriteMovie());
        when(userService.getFavoriteMovies()).thenReturn(favoriteMovies);

        mockMvc.perform(get("/favorites"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(favoriteMovies)));

        verify(userService).getFavoriteMovies();
    }

    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
