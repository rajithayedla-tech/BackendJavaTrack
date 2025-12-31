package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public Course save(Course course) {
        courses.add(course);
        return course;
    }

    public Optional<Course> findById(int id) {
        return courses.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    public Course getById(int id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found: " + id));
    }

    public List<Course> findAll() {
        return List.copyOf(courses); // immutable defensive copy
    }

    public void deleteById(int id) {
        Course course = getById(id);
        courses.remove(course);
    }

    public Course update(Course updatedCourse) {
        Course existing = getById(updatedCourse.getId());
        int index = courses.indexOf(existing);
        courses.set(index, updatedCourse);
        return updatedCourse;
    }
}
