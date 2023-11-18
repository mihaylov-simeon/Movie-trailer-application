package com.moveiapp.final_exam_projectmovieapplication.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review extends BaseEntity {

    private String reviewBody;  // Change 'body' to 'reviewBody'
    private LocalDateTime createdAt;  // Change 'created' to 'createdAt'
    private LocalDateTime updatedAt;  // Change 'updated' to 'updatedAt'

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Review(String reviewBody, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.reviewBody = reviewBody;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
