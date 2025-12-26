package com.airtribe.learntrack.util;

public final class InputValidator {
    private InputValidator() {}

    public static boolean isNonEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean isPositive(int n) {
        return n > 0;
    }
}
