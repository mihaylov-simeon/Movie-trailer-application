package com.moveiapp.final_exam_projectmovieapplication.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    private String name;
    private String email;
    private String password;

    public UserLoginDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
