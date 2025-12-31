package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseService {
    private final List<Course> courses = new ArrayList<Course>();
    public Course addCourse(String name, String desc, int weeks) {
        Course course = new Course(name, desc, weeks, true);
        courses.add(course);
        return course;
    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course with id " + id + " not found");
    }


    public List<Course> listCourses() {
        return Collections.unmodifiableList(courses);
    }

    public void setCourseStatus(int id, boolean active){
        Course course = findById(id);
        course.setActive(active);
    }
}
