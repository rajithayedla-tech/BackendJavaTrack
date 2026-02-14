package com.airtribe.meditrack.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public final class DateUtil {

    // Common formatters used across the application
    public static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private DateUtil() {
        // utility class
    }

    /* =========================
       Parsing & Formatting
       ========================= */

    public static Date parseDate(String dateStr) { try { return sdf.parse(dateStr); } catch (ParseException e) { throw new RuntimeException("Invalid date format. Use yyyy-MM-dd", e); } }

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
