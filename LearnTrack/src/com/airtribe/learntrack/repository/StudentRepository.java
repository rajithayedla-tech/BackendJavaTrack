package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public Student save(Student student) {
        students.add(student);
        return student;
    }

    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }

    public Student getById(int id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + id));
    }

    public List<Student> findAll() {
        return List.copyOf(students); // immutable defensive copy
    }

    public void deleteById(int id) {
        Student student = getById(id);
        students.remove(student);
    }
}
