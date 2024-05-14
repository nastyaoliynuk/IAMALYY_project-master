package com.example.untitled.dto;

public class UserRegistrationDTO {

    // @Pattern(regexp = "^[^\\s]+$", message = "Username cannot contain spaces")
    private String username;

    // @Pattern(regexp = "^[^\\s]+$", message = "Password cannot contain spaces")
    private String password;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}