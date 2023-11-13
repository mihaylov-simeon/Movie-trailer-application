package com.moveiapp.final_exam_projectmovieapplication.service;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;
import com.moveiapp.final_exam_projectmovieapplication.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> findAllMovies() {
        return repository.findAll();
    }

    public Optional<Movie> findMovieByImdbId(String imdbId) {
        return repository.findMovieByImdbId(imdbId);
    }

    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }
}
