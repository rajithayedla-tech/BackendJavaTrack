package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CourseService {
    private final List<Course> courses = new ArrayList<Course>();
    public Course addCourse(String name, String desc, int weeks) {
        Course course = new Course(name, desc, weeks, true);
        courses.add(course);
        return course;
    }

    public Course findById(int id){
        Iterator<Course> iterator = courses.iterator();
        Course course = null;
        while (iterator.hasNext()) {
            course = iterator.next();
            if (course.getId() == id) {
                break;
            }
        }
        return course;
    }

    public List<Course> listCourses() {
        return courses;
    }

    public void setCourseStatus(int id, boolean active){
        for (Course course : courses) {
            if (course.getId() == id) {
                course.setActive(active);
                break;
            }
        }
    }
}
