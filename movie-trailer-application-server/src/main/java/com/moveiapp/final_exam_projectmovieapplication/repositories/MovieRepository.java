package com.moveiapp.final_exam_projectmovieapplication.repositories;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findMovieByImdbId(String imdbId);
}
