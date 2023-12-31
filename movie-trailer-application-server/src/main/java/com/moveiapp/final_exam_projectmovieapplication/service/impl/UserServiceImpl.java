package com.moveiapp.final_exam_projectmovieapplication.service.impl;

import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserLoginDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserRegistrationDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.FavoriteMovie;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.User;
import com.moveiapp.final_exam_projectmovieapplication.repositories.FavoriteMovieRepository;
import com.moveiapp.final_exam_projectmovieapplication.repositories.UserRepository;
import com.moveiapp.final_exam_projectmovieapplication.service.LoggedUser;
import com.moveiapp.final_exam_projectmovieapplication.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;
    private final FavoriteMovieRepository favoriteMovieRepository;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser, FavoriteMovieRepository favoriteMovieRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.favoriteMovieRepository = favoriteMovieRepository;
    }

    @Override
    public boolean register(UserRegistrationDTO userRegistrationDTO) {
        boolean existsByUsernameOrEmail = userRepository.existsByNameAndEmail(
                userRegistrationDTO.getName(),
                userRegistrationDTO.getEmail());

        if (existsByUsernameOrEmail) {
            return false;
        }

        User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();
        User user = userRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword()) && user.getName() != null) {
            loggedUser.login(email);
            userLoginDTO.setName(user.getName());
            user.setActive(true);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public void logout(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            user.setActive(false);
            userRepository.save(user);
        }

        loggedUser.logout();
    }


    @Override
    public List<FavoriteMovie> getFavoriteMovies() {
        User user = userRepository.findByEmail(loggedUser.getEmail());

        if (user != null) {
            System.out.println("User Favorites: " + user.getFavoriteMovies());

            return favoriteMovieRepository.findByUser(user);
        } else {
            return Collections.emptyList();
        }
    }


    @Override
    public void addFavoriteMovie(String imdbId, String title, String poster) {
        User user = userRepository.findByEmail(loggedUser.getEmail());
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.setUser(user);
        favoriteMovie.setImdbId(imdbId);
        favoriteMovie.setTitle(title);
        favoriteMovie.setPoster(poster);
        favoriteMovieRepository.save(favoriteMovie);
    }

    @Override
    public UserRegistrationDTO getUserDetailsByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return new UserRegistrationDTO(user.getName(), user.getEmail(), null);
        } else {
            return null;
        }
    }

    @Override
    public void removeFavoriteMovie(String imdbId) {
        User user = userRepository.findByEmail(loggedUser.getEmail());

        if (user != null) {
            FavoriteMovie favoriteMovie = favoriteMovieRepository.findByImdbIdAndUser(imdbId, user);

            if (favoriteMovie != null) {
                favoriteMovieRepository.delete(favoriteMovie);
            }
        }
    }

    @Override
    public void updateUserName(String email, String name) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            user.setName(name);
            userRepository.save(user);
        }
    }

}
