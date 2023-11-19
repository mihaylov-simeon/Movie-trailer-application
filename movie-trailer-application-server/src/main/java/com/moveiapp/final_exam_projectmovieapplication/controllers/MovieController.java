package com.moveiapp.final_exam_projectmovieapplication.controllers;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;
import com.moveiapp.final_exam_projectmovieapplication.service.impl.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:8080")
public class MovieController {

    private final MovieServiceImpl service;

    public MovieController(MovieServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(service.findAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Movie> getSingleMovie(@PathVariable String imdbId) {
        Optional<Movie> movie = service.findMovieByImdbId(imdbId);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
