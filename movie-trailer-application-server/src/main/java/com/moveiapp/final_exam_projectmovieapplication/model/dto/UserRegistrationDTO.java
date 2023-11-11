package com.moveiapp.final_exam_projectmovieapplication.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationDTO (
        @NotEmpty String name,
        @NotNull @Email String email,
        String password) {
}
