package com.airtribe.lms.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LendingRecord {
    private Book book;
    private LocalDate checkoutDate;
    private LocalDate returnDate;

    public LendingRecord(Book book, LocalDate checkoutDate) {
        this.book = book;
        this.checkoutDate = checkoutDate;
    }

    public void markReturned(LocalDate returnDate) {
        this.returnDate = returnDate;
        book.setBorrowed(false);
    }
}
