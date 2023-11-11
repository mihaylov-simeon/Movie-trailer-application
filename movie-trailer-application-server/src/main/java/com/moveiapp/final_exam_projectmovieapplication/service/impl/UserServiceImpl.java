package com.moveiapp.final_exam_projectmovieapplication.service.impl;

import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserLoginDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.dto.UserRegistrationDTO;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.User;
import com.moveiapp.final_exam_projectmovieapplication.repositories.UserRepository;
import com.moveiapp.final_exam_projectmovieapplication.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean register(UserRegistrationDTO userRegistrationDTO) {
        boolean existsByUsernameOrEmail = userRepository.existsByNameAndEmail(
                userRegistrationDTO.name(),
                userRegistrationDTO.email());

        if (existsByUsernameOrEmail) {
            return false;
        }

        User user = new User();
        user.setName(userRegistrationDTO.name());
        user.setEmail(userRegistrationDTO.email());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.password()));

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.email();
        User user = userRepository.findByEmail(email);

        if (user != null
                && passwordEncoder.matches(userLoginDTO.password(), user.getPassword())) {
            loggedUser.login(email);
            user.setActive(true);
            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }
}
