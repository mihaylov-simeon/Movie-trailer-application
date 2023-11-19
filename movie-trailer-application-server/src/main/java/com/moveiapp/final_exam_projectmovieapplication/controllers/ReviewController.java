package com.moveiapp.final_exam_projectmovieapplication.controllers;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Review;
import com.moveiapp.final_exam_projectmovieapplication.service.impl.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Reviews")
@CrossOrigin(origins = "http://localhost:8080")
public class ReviewController {
    private final ReviewServiceImpl service;

    public ReviewController(ReviewServiceImpl service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        Review review = service.createReview(payload.get("reviewBody"), payload.get("imdbId"));
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }
}
