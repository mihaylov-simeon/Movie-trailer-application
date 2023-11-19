package com.moveiapp.final_exam_projectmovieapplication.service.impl;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Review;
import com.moveiapp.final_exam_projectmovieapplication.repositories.MovieRepository;
import com.moveiapp.final_exam_projectmovieapplication.repositories.ReviewRepository;
import com.moveiapp.final_exam_projectmovieapplication.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Review createReview(String reviewBody, String imdbId) {
        Review review = new Review(reviewBody, LocalDateTime.now(), LocalDateTime.now());
        review.setMovie(movieRepository.findMovieByImdbId(imdbId).orElseThrow(EntityNotFoundException::new)); // Ensure that the movie exists
        reviewRepository.save(review);
        return review;
    }
}
