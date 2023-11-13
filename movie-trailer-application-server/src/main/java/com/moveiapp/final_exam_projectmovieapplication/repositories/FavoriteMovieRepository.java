package com.moveiapp.final_exam_projectmovieapplication.repositories;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.FavoriteMovie;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
    List<FavoriteMovie> findByUser(User user);
}