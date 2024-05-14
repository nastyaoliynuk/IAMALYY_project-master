/*package com.example.untitled.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class JwtResponse {
    private String token;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String name;
    private User.UserStatus status;
    private LocalDate birthday;
    private String infoAboutMe;
    private String location;
    private String avatar;
    public enum UserStatus {
        NONAME,
        MODEL,
        PHOTOGRAF
    }

    public JwtResponse(String token, String password, String username, String email ) {
        this.token = token;
        this.password=password;
        this.username = username;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
*/