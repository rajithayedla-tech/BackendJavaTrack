# Project description

LearnTrack is a simple Java console-based application designed to manage students, courses, and enrollments.  
It demonstrates **core OOP concepts** such as encapsulation, inheritance, polymorphism, static utilities, exception handling, and clean code practices.
---

## Features
- **Student Management**
  - Add new student (with or without email)
  - View all students
  - Search student by ID
  - Deactivate a student (instead of deleting)

- **Course Management**
  - Add new course
  - View all courses
  - Activate/Deactivate a course

- **Enrollment Management**
  - Enroll a student in a course
  - View enrollments for a student
  - Mark enrollment as completed/cancelled

---

# How to compile and run
Run Main Method. Enter inputs from console.


---

## Class Diagram

```mermaid
classDiagram
    class Person {
        - int id
        - String firstName
        - String lastName
        - String email
        + getDisplayName()
    }

    class Student {
        - String batch
        - boolean active
        + getDisplayName()
    }

    class Trainer {
        - String specialization
        + getDisplayName()
    }

    class Course {
        - int id
        - String courseName
        - String description
        - int durationInWeeks
        - boolean active
    }

    class Enrollment {
        - int id
        - int studentId
        - int courseId
        - LocalDate enrollmentDate
        - Status status
    }

    Person <|-- Student
    Person <|-- Trainer
    Student "1" --> "many" Enrollment
    Course "1" --> "many" Enrollment

