package com.airtribe.lms.service;

import com.airtribe.lms.entity.LendingRecord;
import com.airtribe.lms.entity.Patron;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatronService {
    private final Map<Integer, Patron> patrons = new HashMap<>();

    public Patron addPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
        return patron;
    }

    public Patron updatePatron(int id, Patron updatedPatron) {
        Patron existing = patrons.get(id);
        if (existing != null) {
            existing.setName(updatedPatron.getName());
            existing.setEmail(updatedPatron.getEmail());
        }
        return existing;
    }

    public List<LendingRecord> getBorrowingHistory(int patronId) {
        Patron patron = patrons.get(patronId);
        if (patron == null) {
            return Collections.emptyList();
        }
        return patron.getBorrowingHistory();
    }

    public Map<Integer, Patron> getAllPatrons() {
        return patrons;
    }
}
