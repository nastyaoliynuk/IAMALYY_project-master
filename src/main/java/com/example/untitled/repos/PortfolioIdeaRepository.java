package com.example.untitled.repos;


import com.example.untitled.domain.PortfolioIdea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioIdeaRepository extends JpaRepository<PortfolioIdea, Long> {
    List<PortfolioIdea> findAllByDescriptionContainingIgnoreCase(String keyword);

}

