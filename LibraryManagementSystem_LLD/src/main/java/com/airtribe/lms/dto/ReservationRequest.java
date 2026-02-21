package com.airtribe.lms.dto;

public class ReservationRequest {
    private String isbn;
    private int patronId;

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPatronId() {
        return patronId;
    }
    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }
}
