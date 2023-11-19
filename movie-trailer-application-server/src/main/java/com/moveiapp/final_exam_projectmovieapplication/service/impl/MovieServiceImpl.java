package com.moveiapp.final_exam_projectmovieapplication.service.impl;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;
import com.moveiapp.final_exam_projectmovieapplication.repositories.MovieRepository;
import com.moveiapp.final_exam_projectmovieapplication.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Movie> findAllMovies() {
        return repository.findAll();
    }

    @Override
    public Optional<Movie> findMovieByImdbId(String imdbId) {
        return repository.findMovieByImdbId(imdbId);
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }
}
