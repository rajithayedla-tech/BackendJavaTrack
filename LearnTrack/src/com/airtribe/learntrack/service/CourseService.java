package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.repository.CourseRepository;

import java.util.List;

public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String name, String desc, int weeks) {
        validateCourse(name, weeks);
        Course course = new Course(name, desc, weeks, true);
        return courseRepository.save(course);
    }

    public Course findById(int id) {
        return courseRepository.getById(id);
    }

    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(int id, String name, String description, Integer durationInWeeks, Boolean active) {
        Course course = courseRepository.getById(id);

        if (name != null && !name.isBlank()) {
            course.setCourseName(name);
        }
        if (description != null && !description.isBlank()) {
            course.setDescription(description);
        }
        if (durationInWeeks != null && durationInWeeks > 0) {
            course.setDurationInWeeks(durationInWeeks);
        }
        if (active != null) {
            course.setActive(active);
        }

        return courseRepository.update(course);
    }

    public void setCourseStatus(int id, boolean active) {
        Course course = courseRepository.getById(id);
        course.setActive(active);
        courseRepository.update(course);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    private void validateCourse(String name, int weeks) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be blank");
        }
        if (weeks <= 0) {
            throw new IllegalArgumentException("Course duration must be positive");
        }
    }
}
