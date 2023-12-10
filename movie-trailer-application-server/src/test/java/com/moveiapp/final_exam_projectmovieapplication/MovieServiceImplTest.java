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
        String imdbId = "tt123456";
        Movie expectedMovie = new Movie();
        Mockito.when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.of(expectedMovie));

        Optional<Movie> result = movieServiceImpl.findMovieByImdbId(imdbId);

        assertTrue(result.isPresent());
        assertEquals(expectedMovie, result.get());
    }

    @Test
    public void testFindMovieByImdbId_NotFound() {
        String imdbId = "tt789012";
        Mockito.when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.empty());

        Optional<Movie> result = movieServiceImpl.findMovieByImdbId(imdbId);

        assertTrue(result.isEmpty());
    }
}