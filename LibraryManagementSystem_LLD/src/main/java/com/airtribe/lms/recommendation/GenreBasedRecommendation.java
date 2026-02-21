package com.airtribe.lms.recommendation;

import com.airtribe.lms.entity.Book;
import com.airtribe.lms.entity.Patron;

import java.util.List;
import java.util.stream.Collectors;

public class GenreBasedRecommendation implements RecommendationStrategy {
    private final String preferredGenre;

    public GenreBasedRecommendation(String preferredGenre) {
        this.preferredGenre = preferredGenre;
    }

    @Override
    public List<Book> recommendBooks(Patron patron, List<Book> allBooks) {
        // Simplified: assume genre is part of title or author for demo
        return allBooks.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(preferredGenre.toLowerCase())
                        || book.getAuthor().toLowerCase().contains(preferredGenre.toLowerCase()))
                .collect(Collectors.toList());
    }
}
