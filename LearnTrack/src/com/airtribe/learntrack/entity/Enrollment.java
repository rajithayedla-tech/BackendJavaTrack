package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

import java.util.Date;

public class Enrollment {
    public enum Status { ACTIVE, COMPLETED, CANCELLED }
    private int id;
    private Student student;
    private Course course;
    private Date enrollmentDate;
    private Status status;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Enrollment(Student student, Course course, Date enrollmentDate, Status status) {
        this.id = IdGenerator.getNextEnrollmentId();
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}
