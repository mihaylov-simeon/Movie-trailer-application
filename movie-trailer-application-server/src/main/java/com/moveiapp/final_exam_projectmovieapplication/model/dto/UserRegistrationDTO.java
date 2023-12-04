package com.moveiapp.final_exam_projectmovieapplication.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRegistrationDTO {
    @NotEmpty String name;
    @NotNull @Email String email;
    String password;

    public UserRegistrationDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
