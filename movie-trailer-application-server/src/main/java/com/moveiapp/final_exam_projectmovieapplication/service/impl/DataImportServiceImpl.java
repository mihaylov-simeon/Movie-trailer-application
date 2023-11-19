package com.moveiapp.final_exam_projectmovieapplication.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;
import com.moveiapp.final_exam_projectmovieapplication.repositories.MovieRepository;
import com.moveiapp.final_exam_projectmovieapplication.service.DataImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DataImportServiceImpl implements DataImportService {
    private static final Logger logger = LoggerFactory.getLogger(DataImportServiceImpl.class);

    private final MovieRepository movieRepository;

    public DataImportServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void importDataFromJson() throws IOException {
        try {
            logger.info("Importing data from JSON...");

            // Load data from the JSON file
            List<Movie> movies = readMoviesFromJson();

            logger.info("Found {} movies in JSON file.", movies.size());

            List<Movie> allMovies = movieRepository.findAll();

            if (allMovies.isEmpty()) {
                // Save the movies to the database
                movieRepository.saveAll(movies);
            } else {
                return;
            }

            logger.info("Import completed successfully.");
        } catch (Exception e) {
            logger.error("Import failed with the following error: {}", e.getMessage());
        }
    }

    @Override
    public List<Movie> readMoviesFromJson() throws IOException {
        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Read data from the JSON file and convert it to a list of Movie objects
        return objectMapper.readValue(
                getClass().getClassLoader().getResourceAsStream("files/json/movies.json"),
                new TypeReference<List<Movie>>() {}
        );
    }
}
