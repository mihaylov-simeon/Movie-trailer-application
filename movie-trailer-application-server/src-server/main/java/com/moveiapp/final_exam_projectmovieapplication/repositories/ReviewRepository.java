package com.moveiapp.final_exam_projectmovieapplication.repositories;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
