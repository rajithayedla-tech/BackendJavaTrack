package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentIdCounter = 0;
    private static int courseIdCounter = 0;
    private static int enrollmentIdCounter = 0;
    private IdGenerator() {}
    public static int getNextStudentId() {
        if (studentIdCounter == Integer.MAX_VALUE) {
            throw new IllegalStateException("Student ID counter overflow");
        }
        return ++studentIdCounter;
    }

    public static int getNextCourseId() {
        if (courseIdCounter == Integer.MAX_VALUE) {
            throw new IllegalStateException("Course ID counter overflow");
        }
        return ++courseIdCounter;
    }

    public static int getNextEnrollmentId() {
        if (enrollmentIdCounter == Integer.MAX_VALUE) {
            throw new IllegalStateException("Enrollment ID counter overflow");
        }
        return ++enrollmentIdCounter;
    }
}
