package com.airtribe.meditrack.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateUtil {

    // Common formatters used across the application
    public static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private DateUtil() {
        // utility class
    }

    /* =========================
       Parsing & Formatting
       ========================= */

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMAT);
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMAT);
    }

    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMAT);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMAT);
    }

    /* =========================
       Date Utilities
       ========================= */

    public static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    public static boolean isPastDate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    public static LocalDateTime combine(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }

    /* =========================
       Validation
       ========================= */

    public static boolean isValidDate(String date) {
        try {
            parseDate(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidDateTime(String dateTime) {
        try {
            parseDateTime(dateTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
