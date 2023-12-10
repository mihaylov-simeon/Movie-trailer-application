package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.controllers.ReviewController;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.Review;
import com.moveiapp.final_exam_projectmovieapplication.service.impl.ReviewServiceImpl;
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
    private ReviewServiceImpl reviewServiceImpl;

    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reviewController = new ReviewController(reviewServiceImpl);
    }

    @Test
    void testCreateReview() {
        Map<String, String> payload = new HashMap<>();
        payload.put("reviewBody", "This is a review");
        payload.put("imdbId", "12345");

        Review mockReview = new Review();
        Mockito.when(reviewServiceImpl.createReview("This is a review", "12345")).thenReturn(mockReview);

        ResponseEntity<Review> response = reviewController.createReview(payload);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReview, response.getBody());
    }
}
