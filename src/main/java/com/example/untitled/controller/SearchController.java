package com.example.untitled.controller;

import com.example.untitled.domain.PortfolioIdea;
import com.example.untitled.domain.User;
import com.example.untitled.repos.PortfolioIdeaRepository;
import com.example.untitled.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SearchController {

    @Autowired
    private PortfolioIdeaRepository ideaRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/search")
    public ResponseEntity<List<Object>> search(@RequestParam("keyword") String keyword) {
        // Створюємо список для зберігання результатів
        List<Object> searchResults = new ArrayList<>();
        List<PortfolioIdea> ideas;
        List<User> users;

        if (keyword.isEmpty()) {
            ideas = ideaRepository.findAll();
            users= userRepository.findAll();
        } else {
            ideas = ideaRepository.findAllByDescriptionContainingIgnoreCase(keyword);
            users= userRepository.findAllByUsernameContainingIgnoreCase(keyword);

        }

// Додаємо ідеї та користувачів в список результатів
        searchResults.addAll(ideas);
        searchResults.addAll(users);

        return ResponseEntity.ok(searchResults);
    }

   /* @GetMapping("/search/users")
    public ResponseEntity<User> searchUsers(@RequestParam("username") String username) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }*/
}
