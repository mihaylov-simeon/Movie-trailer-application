package com.moveiapp.final_exam_projectmovieapplication.service;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;

import java.io.IOException;
import java.util.List;

public interface DataImportService {
    void importDataFromJson() throws IOException;
    List<Movie> readMoviesFromJson() throws IOException;
}
