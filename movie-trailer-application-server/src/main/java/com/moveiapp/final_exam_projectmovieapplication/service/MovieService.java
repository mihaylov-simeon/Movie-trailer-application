package com.moveiapp.final_exam_projectmovieapplication.service;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAllMovies();
    Optional<Movie> findMovieByImdbId(String imdbId);
    Movie saveMovie(Movie movie);
}