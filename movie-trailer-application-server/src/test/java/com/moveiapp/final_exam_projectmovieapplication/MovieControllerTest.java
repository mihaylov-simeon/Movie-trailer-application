package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.controllers.MovieController;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;
import com.moveiapp.final_exam_projectmovieapplication.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMovies() {
        // Create a list of movies for testing
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        movies.add(new Movie());

        // Mock the behavior of the MovieService to return the list of movies
        Mockito.when(movieService.findAllMovies()).thenReturn(movies);

        // Call the controller method
        ResponseEntity<List<Movie>> response = movieController.getMovies();

        // Assert the response status code and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size()); // Check if it contains 2 movies
    }

    @Test
    public void testGetSingleMovie() {
        // Create a sample IMDb ID for testing
        String imdbId = "tt123456";

        // Create a sample movie for testing
        Movie movie = new Movie();
        Optional<Movie> optionalMovie = Optional.of(movie);

        // Mock the behavior of the MovieService to return the movie
        Mockito.when(movieService.findMovieByImdbId(imdbId)).thenReturn(optionalMovie);

        // Call the controller method
        ResponseEntity<Optional<Movie>> response = movieController.getSingleMovie(imdbId);

        // Assert the response status code and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(movie, response.getBody().get());
    }
}
