package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

        boolean running = true;
        while (true) {
            printMainMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> studentMenu(studentService);
                    case "2" -> courseMenu(courseService);
                    case "3" -> enrollmentMenu(enrollmentService);
                    case "0" -> {
                        System.out.println("Exiting. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("[Error] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("[Unexpected Error] Please try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== LearnTrack ===");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // --- Student Management ---
    private static void studentMenu(StudentService studentService) {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add new student");
        System.out.println("2. View all students");
        System.out.println("3. Search student by ID");
        System.out.println("4. Deactivate student");
        System.out.println("0. Back");
        System.out.print("Choose: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> addStudentFlow(studentService);
            case "2" -> listStudentsFlow(studentService);
            case "3" -> searchStudentFlow(studentService);
            case "4" -> deactivateStudentFlow(studentService);
            case "0" -> {}
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addStudentFlow(StudentService studentService) {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email (optional, press Enter to skip): ");
        String email = scanner.nextLine();
        System.out.print("Batch: ");
        String batch = scanner.nextLine();

        if (!InputValidator.isNonEmpty(firstName) || !InputValidator.isNonEmpty(lastName) || !InputValidator.isNonEmpty(batch)) {
            throw new InvalidInputException("First name, last name, and batch are required.");
        }

        Student s = InputValidator.isNonEmpty(email)
                ? studentService.addStudent(firstName, lastName, email, batch)
                : studentService.addStudent(firstName, lastName, batch);

        System.out.println("Added: " + s.getDisplayName() + " [ID=" + s.getId() + "]");
    }

    private static void listStudentsFlow(StudentService studentService) {
        List<Student> list = studentService.listStudents();
        if (list.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        list.forEach(s -> System.out.println(
                s.getId() + " | " + s.getDisplayName() + " | Batch: " + s.getBatch() + " | Email: " + s.getEmail()));
    }

    private static void searchStudentFlow(StudentService studentService) {
        int id = readInt("Enter student ID: ");
        Student s = studentService.findById(id);
        System.out.println("Found: " + s.getDisplayName() + " | Email: " + s.getEmail() + " | Batch: " + s.getBatch());
    }

    private static void deactivateStudentFlow(StudentService studentService) {
        int id = readInt("Enter student ID to deactivate: ");
        studentService.deactivateStudent(id);
        System.out.println("Student deactivated.");
    }

    // --- Course Management ---
    private static void courseMenu(CourseService courseService) {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add new course");
        System.out.println("2. View all courses");
        System.out.println("3. Activate/Deactivate course");
        System.out.println("0. Back");
        System.out.print("Choose: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> addCourseFlow(courseService);
            case "2" -> listCoursesFlow(courseService);
            case "3" -> toggleCourseFlow(courseService);
            case "0" -> {}
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addCourseFlow(CourseService courseService) {
        System.out.print("Course name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        int weeks = readInt("Duration in weeks: ");

        if (!InputValidator.isNonEmpty(name) || !InputValidator.isPositive(weeks)) {
            throw new InvalidInputException("Course name is required and duration must be positive.");
        }

        Course c = courseService.addCourse(name, desc, weeks);
        System.out.println("Added: " + c.getCourseName() + " [ID=" + c.getId() + "]");
    }

    private static void listCoursesFlow(CourseService courseService) {
        List<Course> list = courseService.listCourses();
        if (list.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        list.forEach(c -> System.out.println(
                c.getId() + " | " + c.getCourseName() + " | " + c.getDescription() +
                        " | Weeks: " + c.getDurationInWeeks() + " | Active: " + c.isActive()));
    }

    private static void toggleCourseFlow(CourseService courseService) {
        int id = readInt("Course ID: ");
        System.out.print("Activate? (y/n): ");
        String yn = scanner.nextLine().trim().toLowerCase();
        boolean activate = yn.startsWith("y");
        courseService.setCourseStatus(id, activate);
        System.out.println("Course " + (activate ? "activated" : "deactivated") + ".");
    }

    // --- Enrollment Management ---
    private static void enrollmentMenu(EnrollmentService enrollmentService) {
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll a student in a course");
        System.out.println("2. View enrollments for a student");
        System.out.println("3. Mark enrollment as completed");
        System.out.println("4. Mark enrollment as cancelled");
        System.out.println("0. Back");
        System.out.print("Choose: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> enrollFlow(enrollmentService);
            case "2" -> viewEnrollmentsFlow(enrollmentService);
            case "3" -> completeEnrollmentFlow(enrollmentService);
            case "4" -> cancelEnrollmentFlow(enrollmentService);
            case "0" -> {}
            default -> System.out.println("Invalid option.");
        }
    }

    private static void enrollFlow(EnrollmentService enrollmentService) {
        int studentId = readInt("Student ID: ");
        int courseId  = readInt("Course ID: ");
        Enrollment e = enrollmentService.enroll(studentId, courseId);
        System.out.println("Enrolled: [Enrollment ID=" + e.getId() + "] on " + e.getEnrollmentDate());
    }

    private static void viewEnrollmentsFlow(EnrollmentService enrollmentService) {
        int studentId = readInt("Student ID: ");
        List<Enrollment> list = enrollmentService.findByStudent(studentId);
        if (list.isEmpty()) {
            System.out.println("No enrollments for student " + studentId);
            return;
        }
        list.forEach(e -> System.out.println(
                e.getId() + " | CourseId: " + e.getCourse().getId() + " | Date: " + e.getEnrollmentDate() + " | Status: " + e.getStatus()));
    }

    private static void completeEnrollmentFlow(EnrollmentService enrollmentService) {
        int id = readInt("Enrollment ID to mark COMPLETED: ");
        enrollmentService.markCompleted(id);
        System.out.println("Enrollment marked COMPLETED.");
    }

    private static void cancelEnrollmentFlow(EnrollmentService enrollmentService) {
        int id = readInt("Enrollment ID to mark CANCELLED: ");
        enrollmentService.markCancelled(id);
        System.out.println("Enrollment marked CANCELLED.");
    }

    // --- Common input helper with exception handling ---
    private static int readInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please enter a valid number.");
        }
    }
}
