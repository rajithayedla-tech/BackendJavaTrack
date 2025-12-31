package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.InputValidator;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(int id, String firstName, String lastName, String email) {
        if (firstName == null || firstName.isBlank()) {
            throw new InvalidInputException("First name cannot be null or blank.");
        } if (lastName == null || lastName.isBlank()) {
            throw new InvalidInputException("Last name cannot be null or blank.");
        } if (email != null && !InputValidator.isValidEmail(email)) {
            throw new InvalidInputException("Invalid email format: " + email);
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            this.email = "(no email)";
        }
        else if (!InputValidator.isValidEmail(email)) {
            throw new InvalidInputException("Invalid email format: " + email);
        } else {
            this.email = email;
        }
    }

    public String getDisplayName() {
        StringBuilder sb = new StringBuilder();

        if (firstName != null && !firstName.isBlank()) {
            sb.append(firstName);
        }
        if (lastName != null && !lastName.isBlank()) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(lastName);
        }

        if (sb.length() == 0) {
            return "(No Name)";
        }
        return sb.toString();
    }

}
