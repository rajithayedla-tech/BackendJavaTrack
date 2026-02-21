package com.airtribe.lms.controller;

import com.airtribe.lms.entity.LendingRecord;
import com.airtribe.lms.entity.Patron;
import com.airtribe.lms.service.LendingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/patrons")
public class PatronController {

    private final LendingService lendingService;

    public PatronController(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
        lendingService.registerPatron(patron);
        return ResponseEntity.ok(patron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable int id, @RequestBody Patron updatedPatron) {
        Patron existing = lendingService.getAllPatrons().get(id);
        if (existing == null) {
            return ResponseEntity.badRequest().build();
        }
        existing.setName(updatedPatron.getName());
        existing.setEmail(updatedPatron.getEmail());
        return ResponseEntity.ok(existing);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<LendingRecord>> getBorrowingHistory(@PathVariable int id) {
        Patron patron = lendingService.getAllPatrons().get(id);
        if (patron == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(patron.getBorrowingHistory());
    }

    // NEW: Get all patrons
    @GetMapping
    public ResponseEntity<Collection<Patron>> getAllPatrons() {
        return ResponseEntity.ok(lendingService.getAllPatrons().values());
    }
}

