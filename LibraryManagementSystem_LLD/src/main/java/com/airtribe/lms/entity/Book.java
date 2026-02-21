package com.airtribe.lms.entity;

import lombok.Data;

@Data
public class Book {
    private int id;                // unique integer ID for the book
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private boolean borrowed = false;

    public Book(int id, String title, String author, String isbn, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }
}
