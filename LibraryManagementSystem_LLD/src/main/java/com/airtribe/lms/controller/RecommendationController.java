package com.airtribe.lms.controller;

import com.airtribe.lms.entity.Book;
import com.airtribe.lms.entity.Patron;
import com.airtribe.lms.recommendation.GenreBasedRecommendation;
import com.airtribe.lms.recommendation.HistoryBasedRecommendation;
import com.airtribe.lms.service.BookService;
import com.airtribe.lms.service.PatronService;
import com.airtribe.lms.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired private RecommendationService recommendationService;
    @Autowired private BookService bookService;
    @Autowired private PatronService patronService;

    @GetMapping("/{patronId}")
    public List<Book> getRecommendations(
            @PathVariable int patronId,
            @RequestParam String strategy,
            @RequestParam(required = false) String genre) {

        Patron patron = patronService.updatePatron(patronId, null); // Simplified: fetch patron by ID
        if (patron == null) {
            throw new IllegalArgumentException("Patron not found");
        }

        // Choose strategy based on query parameter
        switch (strategy.toLowerCase()) {
            case "genre":
                if (genre == null) {
                    throw new IllegalArgumentException("Genre must be provided for genre-based recommendations");
                }
                recommendationService.setStrategy(new GenreBasedRecommendation(genre));
                break;
            case "history":
                recommendationService.setStrategy(new HistoryBasedRecommendation());
                break;
            default:
                throw new IllegalArgumentException("Unknown strategy: " + strategy);
        }

        return recommendationService.getRecommendations(patron, bookService.getAllBooks());
    }
}

