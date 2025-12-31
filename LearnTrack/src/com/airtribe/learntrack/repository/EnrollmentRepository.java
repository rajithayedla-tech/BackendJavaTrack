package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public Enrollment getEnrollmentById(int id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found: " + id));
    }

    public Enrollment updateEnrollmentStatus(int id, Enrollment.Status status) {
        Enrollment enrollment = getEnrollmentById(id);
        enrollment.setStatus(status);
        return enrollment;
    }

    public void deleteEnrollment(int id) {
        Enrollment enrollment = getEnrollmentById(id);
        enrollments.remove(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments); // defensive copy
    }

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        return enrollments.stream()
                .filter(e -> e.getStudent().getId() == studentId)
                .collect(Collectors.toList());
    }

    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        return enrollments.stream()
                .filter(e -> e.getCourse().getId() == courseId)
                .collect(Collectors.toList());
    }

    private Optional<Enrollment> findById(int id) {
        return enrollments.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }
}
