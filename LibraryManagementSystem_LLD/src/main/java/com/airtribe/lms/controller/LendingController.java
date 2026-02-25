package com.airtribe.lms.controller;

import com.airtribe.lms.dto.CheckoutRequest;
import com.airtribe.lms.dto.ReturnRequest;
import com.airtribe.lms.entity.LendingRecord;
import com.airtribe.lms.service.LendingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lendings")
public class LendingController {

    private final LendingService lendingService;

    public LendingController(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    // Checkout a book
    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutBook(@RequestBody CheckoutRequest request) {
        return lendingService.checkoutBook(request.getBookId(), request.getPatronId());
    }

    // Return a book
    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody ReturnRequest request) {
        return lendingService.returnBook(request.getBookId(), request.getPatronId());
    }

    // Get all lending records
    @GetMapping
    public List<LendingRecord> getAllLendingRecords() {
        return lendingService.getAllLendingRecords();
    }
}
