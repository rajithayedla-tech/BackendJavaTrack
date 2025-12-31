package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<Student>();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        Student s = new Student(firstName, lastName, email, batch, true);
        students.add(s);
        return s;
    }

    public Student addStudent(String firstName, String lastName, String batch) {
        Student s = new Student(firstName, lastName, batch, true);
        students.add(s);
        return s;
    }

    public boolean removeStudent(int id){
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public void deactivateStudent(int id) {
        Student s = findById(id);
        s.setActive(false);
    }

    public void updateStudent(int id, String firstName, String lastName, String email, String batch, boolean active) {
        Student student = findById(id);
        student.setFirstName(firstName); student.setLastName(lastName); student.setEmail(email); student.setBatch(batch); student.setActive(active);
    }

    public void updateStudent(int id, String firstName, String lastName, String batch, boolean active) {
        Student student = findById(id);
        student.setFirstName(firstName); student.setLastName(lastName); student.setBatch(batch); student.setActive(active);
    }

    public Student findById(int id){
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student with id " + id + " not found");
    }

    public List<Student> listStudents() {
        return Collections.unmodifiableList(students);
    }
}
