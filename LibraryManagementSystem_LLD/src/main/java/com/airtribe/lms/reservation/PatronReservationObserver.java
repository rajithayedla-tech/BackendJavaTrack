package com.airtribe.lms.reservation;

public class PatronReservationObserver implements ReservationObserver {
    private final int patronId;

    public PatronReservationObserver(int patronId) {
        this.patronId = patronId;
    }

    @Override
    public void notifyAvailable(String isbn) {
        // For now, just log to console. Later you can send email/notification.
        System.out.println("Patron " + patronId + " notified: Book with ISBN " + isbn + " is now available!");
    }
}
