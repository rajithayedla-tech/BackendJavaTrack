package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private final List<Student> students = new ArrayList<Student>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudentById(int id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + id));
    }

    public Student updateStudent(int id, String email, String batch, boolean active) {
        Student student = getStudentById(id);
        if (email != null && !email.isBlank()) {
            student.setEmail(email); // validation handled in setter
        }
        if (batch != null && !batch.isBlank()) {
            student.setBatch(batch);
        }
        student.setActive(active);
        return student;
    }

    public void deleteStudent(int id) {
        Student student = getStudentById(id);
        students.remove(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<Student>(students); // defensive copy
    }

    private Optional<Student> findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
}
