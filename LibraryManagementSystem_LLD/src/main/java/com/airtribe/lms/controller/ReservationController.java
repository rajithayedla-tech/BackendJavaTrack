package com.airtribe.lms.controller;

import com.airtribe.lms.dto.ReservationRequest;
import com.airtribe.lms.entity.Book;
import com.airtribe.lms.reservation.PatronReservationObserver;
import com.airtribe.lms.reservation.ReservationObserver;
import com.airtribe.lms.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Place a reservation
    @PostMapping
    public ResponseEntity<String> reserveBook(@RequestBody ReservationRequest request) {
        ReservationObserver observer = new PatronReservationObserver(request.getPatronId());
        reservationService.reserveBook(request.getIsbn(), observer);
        return ResponseEntity.ok("Reservation placed for ISBN: " + request.getIsbn());
    }

    // Optional: View reservations for an ISBN
    @GetMapping("/{isbn}")
    public ResponseEntity<?> getReservations(@PathVariable String isbn) {
        return ResponseEntity.ok("Reservations exist for ISBN: " + isbn);
    }
}
