package com.moveiapp.final_exam_projectmovieapplication.service;

import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserLoginDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserRegistrationDTO;

public interface UserService {

    boolean register(UserRegistrationDTO userRegistrationDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();
}
