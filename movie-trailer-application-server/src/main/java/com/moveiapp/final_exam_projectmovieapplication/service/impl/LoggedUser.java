package com.moveiapp.final_exam_projectmovieapplication.service.impl;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    @Getter
    private String username;

    private boolean isLogged;

    public boolean isLogged() {
        return isLogged;
    }

    public void login(String username) {
        this.username = username;
        this.isLogged = true;
    }

    public void logout() {
        this.username = null;
        this.isLogged = false;
    }
}
