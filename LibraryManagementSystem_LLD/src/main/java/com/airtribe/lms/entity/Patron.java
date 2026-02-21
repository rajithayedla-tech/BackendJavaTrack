package com.airtribe.lms.entity;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Patron {
    private int patronId;
    private String name;
    private String contactInfo;
    private List<LendingRecord> borrowingHistory = new ArrayList<>();

    public Patron(int patronId, String name, String contactInfo) {
        this.patronId = patronId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void addBorrowingRecord(LendingRecord record) {
        borrowingHistory.add(record);
    }
}

