package com.airtribe.lms.entity;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void updatePatron(Patron patron) {
        patrons.replaceAll(p -> p.getId() == patron.getId() ? patron : p);
    }
}

