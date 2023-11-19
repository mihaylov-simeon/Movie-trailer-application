package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.service.impl.DataImportServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class FinalExamProjectMovieApplication {

    private final DataImportServiceImpl dataImportServiceImpl;

    public FinalExamProjectMovieApplication(DataImportServiceImpl dataImportServiceImpl) {
        this.dataImportServiceImpl = dataImportServiceImpl;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalExamProjectMovieApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        dataImportServiceImpl.importDataFromJson();
    }
}
