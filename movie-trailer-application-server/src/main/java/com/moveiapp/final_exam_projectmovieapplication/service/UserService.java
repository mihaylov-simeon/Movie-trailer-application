package com.moveiapp.final_exam_projectmovieapplication.service;

import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserLoginDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserRegistrationDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.FavoriteMovie;

import java.util.List;

public interface UserService {

    boolean register(UserRegistrationDTO userRegistrationDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout(String email);

    List<FavoriteMovie> getFavoriteMovies();
    
    void addFavoriteMovie(String imdbId, String title, String poster);

    UserRegistrationDTO getUserDetailsByEmail(String email);

    void removeFavoriteMovie(String imdbId);

    void updateUserName(String email, String name);
}
