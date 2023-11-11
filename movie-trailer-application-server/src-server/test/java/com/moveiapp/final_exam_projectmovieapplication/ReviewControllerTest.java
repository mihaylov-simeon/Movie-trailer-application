package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.controllers.ReviewController;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.Review;
import com.moveiapp.final_exam_projectmovieapplication.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        reviewController = new ReviewController(reviewService);
    }

    @Test
    void testCreateReview() {
        // Mock the behavior of the reviewService
        Map<String, String> payload = new HashMap<>();
        payload.put("reviewBody", "This is a review");
        payload.put("imdbId", "12345");

        Review mockReview = new Review();
        Mockito.when(reviewService.createReview("This is a review", "12345")).thenReturn(mockReview);

        // Call the controller method
        ResponseEntity<Review> response = reviewController.createReview(payload);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReview, response.getBody());
    }
}
