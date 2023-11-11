package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.service.DataImportService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalExamProjectMovieApplication {

    private final DataImportService dataImportService;

    public FinalExamProjectMovieApplication(DataImportService dataImportService) {
        this.dataImportService = dataImportService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalExamProjectMovieApplication.class, args);
    }

    @PostConstruct
    public void init() {
        // Import data on application startup
        dataImportService.importDataFromJson();
    }
}
