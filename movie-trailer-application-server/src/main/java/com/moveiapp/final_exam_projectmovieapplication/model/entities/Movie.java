package com.moveiapp.final_exam_projectmovieapplication.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "movies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends BaseEntity {

    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    @Column(length = 2000)
    @ElementCollection
    private List<String> backdrops;

    @ElementCollection
    private List<String> genres;

    @JsonManagedReference
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Movie() {
    }

    public Movie(String imdbId, String title, String releaseDate, String trailerLink, String poster, List<Review> reviews) {
        this.imdbId = imdbId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.trailerLink = trailerLink;
        this.poster = poster;
        this.backdrops = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.reviews = reviews;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setBackdrops(List<String> backdrops) {
        this.backdrops = backdrops;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(imdbId, movie.imdbId) && Objects.equals(title, movie.title) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(trailerLink, movie.trailerLink) && Objects.equals(poster, movie.poster) && Objects.equals(backdrops, movie.backdrops) && Objects.equals(genres, movie.genres) && Objects.equals(reviews, movie.reviews);

    }

    @Override
    public int hashCode() {
        return Objects.hash(imdbId);
    }
}
