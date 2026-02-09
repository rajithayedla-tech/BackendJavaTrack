package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.repository.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        validateName(firstName, lastName);
        validateBatch(batch);

        Student student = new Student(firstName, lastName, email, batch, true);
        return studentRepository.save(student);
    }

    public Student addStudent(String firstName, String lastName, String batch) {
        validateName(firstName, lastName);
        validateBatch(batch);

        Student student = new Student(firstName, lastName, batch, true);
        return studentRepository.save(student);
    }

    public void removeStudent(int id) {
        studentRepository.deleteById(id);
    }

    public void deactivateStudent(int id) {
        Student student = studentRepository.getById(id);
        student.setActive(false);
    }

    public Student updateStudent(int id, String firstName, String lastName, String email, String batch, Boolean active) {
        Student student = studentRepository.getById(id);

        if (firstName != null && !firstName.isBlank()) {
            student.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isBlank()) {
            student.setLastName(lastName);
        }
        if (email != null && !email.isBlank()) {
            student.setEmail(email);
        }
        if (batch != null && !batch.isBlank()) {
            student.setBatch(batch);
        }
        if (active != null) {
            student.setActive(active);
        }

        return student;
    }

    public Student findById(int id) {
        return studentRepository.getById(id);
    }

    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    private void validateName(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be blank");
        }
    }

    private void validateBatch(String batch) {
        if (batch == null || batch.isBlank()) {
            throw new IllegalArgumentException("Batch cannot be blank");
        }
    }
}
