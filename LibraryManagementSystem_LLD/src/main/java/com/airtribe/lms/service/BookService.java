package com.airtribe.lms.service;

import com.airtribe.lms.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> searchBooks(String query) {
        return books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(query)
                        || b.getAuthor().equalsIgnoreCase(query)
                        || b.getIsbn().equalsIgnoreCase(query))
                .collect(Collectors.toList());
    }

    public Book updateBook(String isbn, Book updatedBook) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPublicationYear(updatedBook.getPublicationYear());
                return book;
            }
        }
        return null;
    }

    public void deleteBook(String isbn) {
        books.removeIf(b -> b.getIsbn().equals(isbn));
    }
}