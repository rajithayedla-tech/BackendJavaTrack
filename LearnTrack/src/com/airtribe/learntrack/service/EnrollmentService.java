package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Enrollment.Status;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<Enrollment>();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enroll(int studentId, int courseId) {
        Enrollment e = new Enrollment(studentService.findById(studentId), courseService.findById(courseId), new Date(), Status.ACTIVE);
        enrollments.add(e);
        return e;
    }

    // View enrollments for a student
    public List<Enrollment> findByStudent(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudent().getId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }

    public Enrollment findById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment with id " + id + " not found");
    }

    public void markCompleted(int enrollmentId) {
        Enrollment e = findById(enrollmentId);
        e.setStatus(Status.COMPLETED);
    }

    public void markCancelled(int enrollmentId) {
        Enrollment e = findById(enrollmentId);
        e.setStatus(Status.CANCELLED);
    }
}
