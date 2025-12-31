package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.exception.InvalidInputException;

public class Trainer extends Person{
    private final String subject;
    public Trainer(int id, String firstName, String lastName, String email, String subject) {
        super(id, firstName, lastName, email);
        if (subject == null || subject.isBlank()) {
            throw new InvalidInputException("subject cannot be null or blank.");
        }
        this.subject = subject;
    }

    @Override
    public String getDisplayName() {
        String baseName = super.getDisplayName();
        StringBuilder sb = new StringBuilder(baseName);
        if (getEmail() != null && !getEmail().equals("(no email)")) {
            sb.append(" | ").append(getEmail());
        }
        sb.append(" (Trainer: ").append(subject).append(")");
        return sb.toString();
    }

}
