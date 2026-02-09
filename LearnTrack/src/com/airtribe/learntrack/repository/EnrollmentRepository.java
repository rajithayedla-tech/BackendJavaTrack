package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Enrollment save(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    public Optional<Enrollment> findById(int id) {
        return enrollments.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public Enrollment getById(int id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found: " + id));
    }

    public List<Enrollment> findAll() {
        return List.copyOf(enrollments); // immutable defensive copy
    }

    public void deleteById(int id) {
        Enrollment enrollment = getById(id);
        enrollments.remove(enrollment);
    }

    public Enrollment updateStatus(int id, Enrollment.Status status) {
        Enrollment enrollment = getById(id);
        enrollment.setStatus(status);
        return enrollment;
    }

    public List<Enrollment> findByStudentId(int studentId) {
        return enrollments.stream()
                .filter(e -> e.getStudent().getId() == studentId)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Enrollment> findByCourseId(int courseId) {
        return enrollments.stream()
                .filter(e -> e.getCourse().getId() == courseId)
                .collect(Collectors.toUnmodifiableList());
    }
}
