package com.moveiapp.final_exam_projectmovieapplication.controllers;

import com.moveiapp.final_exam_projectmovieapplication.model.dto.FavoriteMovieDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserLoginDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserRegistrationDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.FavoriteMovie;
import com.moveiapp.final_exam_projectmovieapplication.service.LoggedUser;
import com.moveiapp.final_exam_projectmovieapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;

    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        if (loggedUser.isLogged()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is already logged in.");
        }

        UserRegistrationDTO userDetails = userService.getUserDetailsByEmail(userLoginDTO.getEmail());

        if (userDetails != null && userService.login(userLoginDTO)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("name", userLoginDTO.getName());

            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        if (loggedUser.isLogged()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is already logged in.");
        }

        if (userService.register(userRegistrationDTO)) {
            return ResponseEntity.ok().body("Registration successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        if (!loggedUser.isLogged()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not logged in.");
        }

        this.userService.logout(loggedUser.getEmail());

        loggedUser.logout();

        return ResponseEntity.ok().body("Logout successful");
    }

    @GetMapping("/favorites")
    @ResponseBody
    public ResponseEntity<List<FavoriteMovie>> getFavoriteMovies(@RequestHeader HttpHeaders headers) {
        try {
            System.out.println("Headers: " + headers);
            System.out.println("Endpoint /favorites accessed.");
            List<FavoriteMovie> favoriteMovies = userService.getFavoriteMovies();
            System.out.println("Movies: " + favoriteMovies);
            return ResponseEntity.ok(favoriteMovies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }


    @PostMapping("/add-favorite")
    @ResponseBody
    public ResponseEntity<List<FavoriteMovie>> addFavoriteMovie(@RequestBody FavoriteMovieDTO movieDTO) {
        userService.addFavoriteMovie(movieDTO.getImdbId(), movieDTO.getTitle(), movieDTO.getPoster());
        List<FavoriteMovie> favoriteMovies = userService.getFavoriteMovies();
        return ResponseEntity.ok(favoriteMovies);
    }

    @DeleteMapping("/remove-favorite/{imdbId}")
    @ResponseBody
    public ResponseEntity<List<FavoriteMovie>> removeFavoriteMovie(@PathVariable String imdbId) {
        userService.removeFavoriteMovie(imdbId);
        List<FavoriteMovie> favoriteMovies = userService.getFavoriteMovies();
        return ResponseEntity.ok(favoriteMovies);
    }

    @RequestMapping("/user")
    @ResponseBody
    public ResponseEntity<?> getUserDetails() {
        try {
            if (loggedUser.isLogged()) {
                UserRegistrationDTO userDetails = userService.getUserDetailsByEmail(loggedUser.getEmail());

                if (userDetails != null) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("email", userDetails.getEmail());
                    response.put("name", userDetails.getName());

                    return ResponseEntity.ok().body(response);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyMap());
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyMap());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyMap());
        }
    }

    @PutMapping("/user")
    @ResponseBody
    public ResponseEntity<?> updateUserName(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            if (loggedUser.isLogged()) {
                userService.updateUserName(loggedUser.getEmail(), userRegistrationDTO.getName());
                return ResponseEntity.ok().body("Name updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update name");
        }
    }


}

