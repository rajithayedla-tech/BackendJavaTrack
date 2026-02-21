package com.airtribe.lms.reservation;

import com.airtribe.lms.entity.Patron;

public class PatronReservationObserver implements ReservationObserver {
    private final Patron patron;

    public PatronReservationObserver(Patron patron) {
        this.patron = patron;
    }

    @Override
    public void notifyAvailable(String isbn) {
        System.out.println("Notification to Patron " + patron.getName() +
                ": Book with ISBN " + isbn + " is now available!");
    }
}

