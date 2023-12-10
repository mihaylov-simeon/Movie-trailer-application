package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.controllers.MovieController;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;
import com.moveiapp.final_exam_projectmovieapplication.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @Mock
    private MovieServiceImpl movieServiceImpl;

    @InjectMocks
    private MovieController movieController;

    @Test
    void getMovies() throws Exception {
        // Arrange
        List<Movie> movies = Collections.singletonList(new Movie());
        when(movieServiceImpl.findAllMovies()).thenReturn(movies);

        // Act & Assert
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getSingleMovie() throws Exception {
        String imdbId = "exampleImdbId";
        Movie movie = new Movie();
        when(movieServiceImpl.findMovieByImdbId(imdbId)).thenReturn(Optional.of(movie));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/{imdbId}", imdbId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getSingleMovie_NotFound() throws Exception {
        String imdbId = "nonExistingImdbId";
        when(movieServiceImpl.findMovieByImdbId(imdbId)).thenReturn(Optional.empty());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/{imdbId}", imdbId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
