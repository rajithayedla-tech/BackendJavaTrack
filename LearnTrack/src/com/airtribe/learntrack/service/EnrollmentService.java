package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.repository.EnrollmentRepository;

import java.util.Date;
import java.util.List;

public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.enrollmentRepository = new EnrollmentRepository();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enroll(int studentId, int courseId) {
        Student student = studentService.findById(studentId);
        Course course = courseService.findById(courseId);

        if (!student.isActive()) {
            throw new IllegalStateException("Cannot enroll inactive student: " + studentId);
        }
        if (!course.isActive()) {
            throw new IllegalStateException("Cannot enroll in inactive course: " + courseId);
        }

        Enrollment enrollment = new Enrollment(student, course, new Date(), Enrollment.Status.ACTIVE);
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment findById(int id) {
        return enrollmentRepository.getById(id);
    }

    public List<Enrollment> listAll() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> findByStudent(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> findByCourse(int courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public void markCompleted(int id) {
        enrollmentRepository.updateStatus(id, Enrollment.Status.COMPLETED);
    }

    public void markCancelled(int id) {
        enrollmentRepository.updateStatus(id, Enrollment.Status.CANCELLED);
    }

    public void deleteEnrollment(int id) {
        enrollmentRepository.deleteById(id);
    }
}
