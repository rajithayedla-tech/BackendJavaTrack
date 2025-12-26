package com.airtribe.learntrack.entity;

public class Trainer extends Person{
    private String subject;
    public Trainer(int id, String firstName, String lastName, String email, String subject) {
        super(id, firstName, lastName, email);
        this.subject = subject;
    }

    @Override
    public String getDisplayName() {
        return "Trainer: " + super.getDisplayName() + " [Subject: " + subject + "]";
    }
}
