package com.example.untitled.controller;

import com.example.untitled.domain.User;
import com.example.untitled.repos.UserRepository;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://192.168.0.186:4200")

@RequestMapping("/profile")
public class UserDetailsController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody User updatedUser) throws NumberParseException {
        User existingUser = userRepository.findByUsernameAndPassword(updatedUser.getUsername(), updatedUser.getPassword());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setInfo_about_me(updatedUser.getInfo_about_me());
        existingUser.setLocation(updatedUser.getLocation());
        existingUser.setAvatar(updatedUser.getAvatar());
        existingUser.setStatus(updatedUser.getStatus());
        existingUser.setB_day(updatedUser.getB_day());


        userRepository.save(existingUser);

        return ResponseEntity.ok("Profile updated successfully");
    }}




