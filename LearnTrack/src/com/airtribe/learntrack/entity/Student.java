package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student(String firstName, String lastName, String email, String batch, boolean active) {
        super(IdGenerator.getNextStudentId(), firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    public Student(String firstName, String lastName, String batch, boolean active) {
        super(IdGenerator.getNextStudentId(), firstName, lastName, null);
        this.batch = batch;
        this.active = active;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        String baseName = super.getDisplayName();
        StringBuilder sb = new StringBuilder(baseName);

        if (getEmail() != null && !getEmail().equals("(no email)")) {
            sb.append(" | ").append(getEmail());
        }

        sb.append(" [").append(isActive() ? "ACTIVE" : "INACTIVE").append("]");
        return sb.toString();
    }

}
