package com.airtribe.lms.dto;

public class ReturnRequest {
    private int bookId;
    private int patronId;

    public ReturnRequest() {
    }

    public ReturnRequest(int bookId, int patronId) {
        this.bookId = bookId;
        this.patronId = patronId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }
}

