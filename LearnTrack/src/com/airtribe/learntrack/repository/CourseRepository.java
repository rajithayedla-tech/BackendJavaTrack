package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Course getCourseById(int id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found: " + id));
    }

    public Course updateCourse(int id, String name, String description, Integer durationInWeeks, Boolean active) {
        Course course = getCourseById(id);

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

        return course;
    }

    public void deleteCourse(int id) {
        Course course = getCourseById(id);
        courses.remove(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses); // defensive copy
    }

    private Optional<Course> findById(int id) {
        return courses.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
}
