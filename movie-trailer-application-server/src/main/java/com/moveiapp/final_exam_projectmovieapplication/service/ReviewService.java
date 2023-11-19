package com.moveiapp.final_exam_projectmovieapplication.service;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Review;

public interface ReviewService {
    Review createReview(String reviewBody, String imdbId);
}