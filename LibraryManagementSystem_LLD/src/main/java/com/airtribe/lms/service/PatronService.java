package com.airtribe.lms.service;

import com.airtribe.lms.entity.LendingRecord;
import com.airtribe.lms.entity.Patron;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatronService {
    private final List<Patron> patrons = new ArrayList<>();

    public Patron addPatron(Patron patron) {
        patrons.add(patron);
        return patron;
    }

    public Patron updatePatron(int id, Patron updatedPatron) {
        for (Patron patron : patrons) {
            if (patron.getPatronId() == id) {
                patron.setName(updatedPatron.getName());
                patron.setContactInfo(updatedPatron.getContactInfo());
                return patron;
            }
        }
        return null;
    }

    public List<LendingRecord> getBorrowingHistory(int patronId) {
        return patrons.stream()
                .filter(p -> p.getPatronId() == patronId)
                .findFirst()
                .map(Patron::getBorrowingHistory)
                .orElse(new ArrayList<>());
    }
}

