package com.moveiapp.final_exam_projectmovieapplication.controllers;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;
import com.moveiapp.final_exam_projectmovieapplication.service.MovieService;
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

    private final MovieService service;
//    private final MovieImport movieImport;

    public MovieController(MovieService service) {
        this.service = service;
//        this.movieImport = movieImport;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(service.findAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId) {
        return new ResponseEntity<>(service.findMovieByImdbId(imdbId), HttpStatus.OK);
    }

//    @PostMapping("/")
//    public ModelAndView importMovies() {
//        ModelAndView modelAndView = new ModelAndView("import-result");
//        try {
//            modelAndView.addObject("importReport", this.movieImport.importMovies());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return modelAndView;
//    }

}
