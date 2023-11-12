package com.moveiapp.final_exam_projectmovieapplication.controllers;

import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserLoginDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserRegistrationDTO;
import com.moveiapp.final_exam_projectmovieapplication.service.UserService;
import com.moveiapp.final_exam_projectmovieapplication.service.impl.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
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

        if (userService.login(userLoginDTO)) {

            return ResponseEntity.ok().body("Login successful");
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

        // Call the service to mark the user as active: false
        this.userService.logout(loggedUser.getEmail());

        // Clear the authentication state
        loggedUser.logout();

        return ResponseEntity.ok().body("Logout successful");
    }
}
