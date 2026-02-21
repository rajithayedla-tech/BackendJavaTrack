package com.airtribe.lms.service;


import com.airtribe.lms.entity.Book;
import com.airtribe.lms.entity.Patron;
import com.airtribe.lms.recommendation.RecommendationStrategy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final LendingService lendingService;

    public RecommendationService(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    public List<Book> recommendBooks(int patronId, String strategy) {
        Patron patron = lendingService.getAllPatrons().get(patronId);
        if (patron == null) {
            throw new IllegalArgumentException("Patron not found: " + patronId);
        }

        if ("history".equalsIgnoreCase(strategy)) {
            Set<String> authors = patron.getBorrowingHistory().stream()
                    .map(record -> {
                        Book b = lendingService.getAllBooks().get(record.getBookId());
                        return b != null ? b.getAuthor() : null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            return lendingService.getAllBooks().values().stream()
                    .filter(book -> authors.contains(book.getAuthor()) && !book.isBorrowed())
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}


