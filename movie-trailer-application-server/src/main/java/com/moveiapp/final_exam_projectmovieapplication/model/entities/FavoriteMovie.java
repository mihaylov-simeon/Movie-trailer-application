package com.moveiapp.final_exam_projectmovieapplication.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Getter
@Entity
@Data
@Table(name = "favorite_movie")
public class FavoriteMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String imdbId;

    private String title;

    private String poster;

    public FavoriteMovie() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
