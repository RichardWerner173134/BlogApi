package com.blog.api.api.controller;

import com.blog.api.api.model.dao.UserDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;
    private UserData userData;

    @AllArgsConstructor
    static class UserData{
        private String author;
        private String vorname;
        private String nachname;
        private String profilbild;
    }
}
