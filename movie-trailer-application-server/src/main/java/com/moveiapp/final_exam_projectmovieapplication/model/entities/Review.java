package com.moveiapp.final_exam_projectmovieapplication.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
