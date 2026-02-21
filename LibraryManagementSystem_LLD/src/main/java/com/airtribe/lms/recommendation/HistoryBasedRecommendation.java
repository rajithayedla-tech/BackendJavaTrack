package com.airtribe.lms.recommendation;

import com.airtribe.lms.entity.Book;
import com.airtribe.lms.entity.Patron;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryBasedRecommendation implements RecommendationStrategy {
    @Override
    public List<Book> recommendBooks(Patron patron, List<Book> allBooks) {
        List<String> borrowedTitles = patron.getBorrowingHistory().stream()
                .map(record -> record.getBook().getTitle())
                .collect(Collectors.toList());

        // Recommend books with similar titles (simplified demo)
        return allBooks.stream()
                .filter(book -> borrowedTitles.stream().anyMatch(title -> book.getTitle().contains(title)))
                .collect(Collectors.toList());
    }
}
