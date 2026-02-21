package com.airtribe.lms.service;

import com.airtribe.lms.entity.Book;
import com.airtribe.lms.entity.LendingRecord;
import com.airtribe.lms.entity.Patron;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class LendingService {

    private final Map<Integer, Book> books = new HashMap<>();
    private final Map<Integer, Patron> patrons = new HashMap<>();
    private final List<LendingRecord> lendingRecords = new ArrayList<>();
    private final ReservationService reservationService;


    // Register a book
    public void registerBook(Book book) {
        books.put(book.getId(), book);
    }

    // Register a patron
    public void registerPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
    }

    // Checkout a book
    public ResponseEntity<?> checkoutBook(int bookId, int patronId) {
        Book book = books.get(bookId);
        Patron patron = patrons.get(patronId);

        if (book == null) {
            return ResponseEntity.badRequest().body("Book not found");
        }
        if (patron == null) {
            return ResponseEntity.badRequest().body("Patron not found");
        }
        if (book.isBorrowed()) {
            return ResponseEntity.badRequest().body("Book already borrowed");
        }

        book.setBorrowed(true);
        LendingRecord record = new LendingRecord(bookId, patronId, LocalDate.now());
        lendingRecords.add(record);
        patron.addBorrowingRecord(record);

        return ResponseEntity.ok(record);
    }

    // Return a book
    public ResponseEntity<?> returnBook(int bookId, int patronId) {
        Book book = books.get(bookId);
        Patron patron = patrons.get(patronId);

        if (book == null) {
            return ResponseEntity.badRequest().body("Book not found");
        }
        if (patron == null) {
            return ResponseEntity.badRequest().body("Patron not found");
        }
        if (!book.isBorrowed()) {
            return ResponseEntity.badRequest().body("Book is not currently borrowed");
        }

        book.setBorrowed(false);

        Optional<LendingRecord> recordOpt = lendingRecords.stream()
                .filter(r -> r.getBookId() == bookId && r.getPatronId() == patronId && r.getReturnDate() == null)
                .findFirst();

        if (recordOpt.isPresent()) {
            LendingRecord record = recordOpt.get();
            record.setReturnDate(LocalDate.now());

            // 🔔 Notify ReservationService when book becomes available
            reservationService.notifyAvailability(book);

            return ResponseEntity.ok(record);
        } else {
            return ResponseEntity.badRequest().body("No active lending record found for this patron and book");
        }
    }


    // Get all lending records
    public List<LendingRecord> getAllLendingRecords() {
        return lendingRecords;
    }

    // Get all patrons
    public Map<Integer, Patron> getAllPatrons() {
        return patrons;
    }

    // Get all books
    public Map<Integer, Book> getAllBooks() {
        return books;
    }

    public LendingService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}
