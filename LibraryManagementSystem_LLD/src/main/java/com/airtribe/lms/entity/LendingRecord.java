package com.airtribe.lms.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LendingRecord {
    private int bookId;
    private int patronId;
    private LocalDate checkoutDate;
    private LocalDate returnDate;

    public LendingRecord(int bookId, int patronId, LocalDate checkoutDate) {
        this.bookId = bookId;
        this.patronId = patronId;
        this.checkoutDate = checkoutDate;
    }

    public void markReturned(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
