package com.airtribe.lms.service;

import com.airtribe.lms.entity.Book;
import com.airtribe.lms.entity.LendingRecord;
import com.airtribe.lms.entity.Patron;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LendingService {
    private final List<LendingRecord> lendingRecords = new ArrayList<>();
    private final BookService bookService;
    private final PatronService patronService;
    private final ReservationService reservationService;

    public LendingService(BookService bookService, PatronService patronService, ReservationService reservationService) {
        this.bookService = bookService;
        this.patronService = patronService;
        this.reservationService = reservationService;
    }

    public String checkoutBook(String isbn, int patronId) {
        Book book = bookService.getAllBooks().stream()
                .filter(b -> b.getIsbn().equals(isbn) && !b.isBorrowed())
                .findFirst()
                .orElse(null);

        if (book == null) {
            return "Book not available.";
        }

        // Simplified: In a real system, fetch Patron by ID from PatronService
        Patron patron = patronService.getBorrowingHistory(patronId) != null ? new Patron(patronId, "Unknown", "N/A") : null;

        if (patron == null) {
            return "Patron not found.";
        }

        book.setBorrowed(true);
        LendingRecord record = new LendingRecord(book, LocalDate.now());
        lendingRecords.add(record);
        patron.addBorrowingRecord(record);

        return "Book checked out successfully.";
    }

    public String returnBook(String isbn) {
        for (LendingRecord record : lendingRecords) {
            if (record.getBook().getIsbn().equals(isbn) && record.getReturnDate() == null) {
                record.markReturned(LocalDate.now());
                // Notify reservation system that the book is now available
                reservationService.notifyAvailability(record.getBook());
                return "Book returned successfully.";
            }
        }
        return "Book not found in lending records.";
    }

    public List<LendingRecord> getAllLendingRecords() {
        return lendingRecords;
    }
}
