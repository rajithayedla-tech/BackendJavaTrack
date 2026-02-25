package com.airtribe.lms.entity;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Patron {
    private int id;
    private String name;
    private String email;
    private List<LendingRecord> borrowingHistory = new ArrayList<>();

    public Patron(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void addBorrowingRecord(LendingRecord record) {
        borrowingHistory.add(record);
    }
}
