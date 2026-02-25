package com.airtribe.lms.recommendation;

import com.airtribe.lms.entity.Book;
import com.airtribe.lms.entity.LendingRecord;
import com.airtribe.lms.entity.Patron;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryBasedRecommendation implements RecommendationStrategy {

    @Override
    public List<Book> recommendBooks(Patron patron, List<Book> allBooks) {
        // Collect IDs of books the patron has borrowed before
        List<Integer> borrowedBookIds = patron.getBorrowingHistory().stream()
                .map(LendingRecord::getBookId)
                .collect(Collectors.toList());

        // Recommend books with the same IDs or similar titles (simplified demo)
        return allBooks.stream()
                .filter(book -> borrowedBookIds.contains(book.getId()))
                .collect(Collectors.toList());
    }
}
