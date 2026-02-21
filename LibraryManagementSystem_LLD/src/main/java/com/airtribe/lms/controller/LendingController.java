package com.airtribe.lms.controller;

import com.airtribe.lms.service.LendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lendings")
public class LendingController {
    @Autowired
    private LendingService lendingService;

    @PostMapping("/checkout") public ResponseEntity<String> checkoutBook(@RequestParam String isbn, @RequestParam int patronId) {
        return ResponseEntity.ok(lendingService.checkoutBook(isbn, patronId));
    }

    @PostMapping("/return") public ResponseEntity<String> returnBook(@RequestParam String isbn) {
        return ResponseEntity.ok(lendingService.returnBook(isbn));
    }
}

