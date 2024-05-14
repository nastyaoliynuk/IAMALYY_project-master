package com.example.untitled.controller;

import com.example.untitled.config.jwt.JwtUtils;
import com.example.untitled.domain.User;
import com.example.untitled.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://192.168.0.186:4200")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {
        User user = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user != null) {
            String token = jwtUtils.generateJwtToken(user);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}


