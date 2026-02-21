package com.airtribe.lms.service;

import com.airtribe.lms.entity.Book;
import com.airtribe.lms.reservation.ReservationObserver;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final Map<String, List<ReservationObserver>> reservations = new HashMap<>();

    public void reserveBook(String isbn, ReservationObserver observer) {
        reservations.computeIfAbsent(isbn, k -> new ArrayList<>()).add(observer);
        System.out.println("Reservation placed for ISBN: " + isbn);
    }

    public void notifyAvailability(Book book) {
        String isbn = book.getIsbn();
        if (reservations.containsKey(isbn)) {
            for (ReservationObserver observer : reservations.get(isbn)) {
                observer.notifyAvailable(isbn);
            }
            reservations.remove(isbn); // Clear reservations once notified
        }
    }
}

