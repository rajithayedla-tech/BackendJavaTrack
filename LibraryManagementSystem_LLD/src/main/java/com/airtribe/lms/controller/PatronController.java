package com.airtribe.lms.controller;

import com.airtribe.lms.entity.LendingRecord;
import com.airtribe.lms.entity.Patron;
import com.airtribe.lms.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrons")
public class PatronController {
    @Autowired private PatronService patronService;

    @PostMapping public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
        return ResponseEntity.ok(patronService.addPatron(patron));
    }

    @PutMapping("/{id}") public ResponseEntity<Patron> updatePatron(@PathVariable int id, @RequestBody Patron patron) {
        return ResponseEntity.ok(patronService.updatePatron(id, patron));
    }

    @GetMapping("/{id}/history") public ResponseEntity<List<LendingRecord>> getBorrowingHistory(@PathVariable int id) {
        return ResponseEntity.ok(patronService.getBorrowingHistory(id));
    }
}

