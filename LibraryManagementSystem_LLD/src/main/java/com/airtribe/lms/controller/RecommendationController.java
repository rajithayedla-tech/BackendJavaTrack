package com.airtribe.lms.controller;

import com.airtribe.lms.entity.Book;
import com.airtribe.lms.entity.Patron;
import com.airtribe.lms.recommendation.GenreBasedRecommendation;
import com.airtribe.lms.recommendation.HistoryBasedRecommendation;
import com.airtribe.lms.service.BookService;
import com.airtribe.lms.service.PatronService;
import com.airtribe.lms.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{patronId}")
    public ResponseEntity<List<Book>> getRecommendations(
            @PathVariable int patronId,
            @RequestParam(defaultValue = "history") String strategy) {
        return ResponseEntity.ok(recommendationService.recommendBooks(patronId, strategy));
    }
}


