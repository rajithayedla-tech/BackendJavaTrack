package com.airtribe.lms.recommendation;

import com.airtribe.lms.entity.Book;
import com.airtribe.lms.entity.Patron;

import java.util.List;

public interface RecommendationStrategy {
    List<Book> recommendBooks(Patron patron, List<Book> allBooks);
}

