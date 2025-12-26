package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<Student>();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        int id = IdGenerator.getNextStudentId();
        Student s = new Student(firstName, lastName, email, batch, true);
        students.add(s);
        return s;
    }

    // Overloaded add: without email (constructor overloading in Student)
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

    public boolean updateStudent(int id, String firstName, String lastName, String email, String batch, boolean active) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setFirstName(firstName); s.setLastName(lastName);
                s.setEmail(email);
                s.setBatch(batch);
                s.setActive(active);
                return true;
            }
        }
        return false;
    }

    public boolean updateStudent(int id, String firstName, String lastName, String batch, boolean active) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setFirstName(firstName); s.setLastName(lastName);
                s.setBatch(batch);
                s.setActive(active);
                return true;
            }
        }
        return false;
    }

    public Student findById(int id){
        Iterator<Student> iterator = students.iterator();
        Student student = null;
        while (iterator.hasNext()) {
            student = iterator.next();
            if (student.getId() == id) {
                break;
            }
        }
        return student;
    }

    public List<Student> listStudents() {
        return students;
    }
}
