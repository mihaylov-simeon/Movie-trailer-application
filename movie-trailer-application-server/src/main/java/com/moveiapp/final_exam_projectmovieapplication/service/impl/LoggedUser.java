package com.moveiapp.final_exam_projectmovieapplication.service.impl;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    @Getter
    private String email;

    private boolean isLogged;

    public boolean isLogged() {
        return isLogged;
    }

    public void login(String username) {
        this.email = username;
        this.isLogged = true;
    }

    public void logout() {
        this.email = null;
        this.isLogged = false;
    }
}
