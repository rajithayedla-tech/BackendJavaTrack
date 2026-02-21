package com.airtribe.lms.service;


import com.airtribe.lms.entity.Book;
import com.airtribe.lms.entity.Patron;
import com.airtribe.lms.recommendation.RecommendationStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {
    private RecommendationStrategy strategy;

    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Book> getRecommendations(Patron patron, List<Book> allBooks) {
        if (strategy == null) {
            throw new IllegalStateException("Recommendation strategy not set.");
        }
        return strategy.recommendBooks(patron, allBooks);
    }
}

