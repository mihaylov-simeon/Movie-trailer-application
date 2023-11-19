package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;
import com.moveiapp.final_exam_projectmovieapplication.repositories.MovieRepository;
import com.moveiapp.final_exam_projectmovieapplication.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindMovieByImdbId() {
        // Mock the behavior of the repository to return an Optional with a Movie
        String imdbId = "tt123456";
        Movie expectedMovie = new Movie();
        Mockito.when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.of(expectedMovie));

        // Call the service method
        Optional<Movie> result = movieServiceImpl.findMovieByImdbId(imdbId);

        // Assert that the result is not empty and contains the expected movie
        assertTrue(result.isPresent());
        assertEquals(expectedMovie, result.get());
    }

    @Test
    public void testFindMovieByImdbId_NotFound() {
        // Mock the behavior of the repository to return an empty Optional
        String imdbId = "tt789012";
        Mockito.when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.empty());

        // Call the service method
        Optional<Movie> result = movieServiceImpl.findMovieByImdbId(imdbId);

        // Assert that the result is empty
        assertTrue(result.isEmpty());
    }
}