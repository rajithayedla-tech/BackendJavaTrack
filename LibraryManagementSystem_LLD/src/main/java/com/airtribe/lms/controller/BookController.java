package com.airtribe.lms.controller;

import com.airtribe.lms.entity.Book;
import com.airtribe.lms.service.LendingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {

    private final LendingService lendingService;

    public BookController(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        lendingService.registerBook(book);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        Book existing = lendingService.getAllBooks().get(id);
        if (existing == null) {
            return ResponseEntity.badRequest().build();
        }
        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setIsbn(updatedBook.getIsbn());
        existing.setPublicationYear(updatedBook.getPublicationYear());
        return ResponseEntity.ok(existing);
    }

    // NEW: Get all books
    @GetMapping
    public ResponseEntity<Collection<Book>> getAllBooks() {
        return ResponseEntity.ok(lendingService.getAllBooks().values());
    }
}
