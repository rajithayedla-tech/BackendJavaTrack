package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;

public final class Validator {

    private Validator() {}

    public static void notNull(Object obj, String message)
            throws InvalidDataException {
        if (obj == null) {
            throw new InvalidDataException(message);
        }
    }

    public static void notEmpty(String value, String fieldName)
            throws InvalidDataException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidDataException(fieldName + " cannot be empty");
        }
    }

    public static void positive(int value, String fieldName)
            throws InvalidDataException {
        if (value <= 0) {
            throw new InvalidDataException(fieldName + " must be positive");
        }
    }

    public static void positive(double value, String fieldName)
            throws InvalidDataException {
        if (value <= 0) {
            throw new InvalidDataException(fieldName + " must be positive");
        }
    }
}
