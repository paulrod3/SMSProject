package jpa.service;

import jpa.entitymodels.Course;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SMSRunner {

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Please select an option:");
                System.out.println("1. Student");
                System.out.println("2. Quit");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.println("Enter your email:");
                        String email = scanner.nextLine();
                        System.out.println("Enter your password:");
                        String password = scanner.nextLine();
                        try {
                            if (!studentService.validateStudent(email, password)) {
                                System.out.println("Invalid credentials. Exiting.");
                                return;
                            }

                            List<Course> registeredCourses = studentService.getStudentCourses(email);
                            System.out.println("You are registered in the following courses:");
                            if (registeredCourses.isEmpty()) {
                                System.out.println("-----No courses currently associated with the student.-----");
                            } else {
                                System.out.println("ID : Course Name : Instructor Name");
                                registeredCourses.forEach(course -> System.out.println
                                        (course.getCId() + ": " + course.getCName() + " - " + course.getCInstructorName()));
                            }

                            while (true) {
                                System.out.println("Select an option:");
                                System.out.println("1. Register to Class");
                                System.out.println("2. Logout");
                                String studentChoice = scanner.nextLine();

                                switch (studentChoice) {
                                    case "1" -> {
                                        try {
                                            List<Course> allCourses = courseService.getAllCourses();
                                            System.out.println("All Courses:");
                                            System.out.println("ID : Course Name : Instructor Name");
                                            allCourses.forEach(course -> System.out.println
                                                    (course.getCId() + ": " + course.getCName() + " - " + course.getCInstructorName()));
                                            System.out.println("Enter the course ID you want to register for:");
                                            int courseId = scanner.nextInt();
                                            scanner.nextLine();
                                            // Check if student is already registered for the course
                                            boolean alreadyRegistered = false;
                                            for (Course registeredCourse : registeredCourses) {
                                                if (registeredCourse.getCId() == courseId) {
                                                    alreadyRegistered = true;
                                                    break;
                                                }
                                            }

                                            if (alreadyRegistered) {
                                                System.out.println("You are already registered in that course!");
                                            } else {
                                                boolean success = studentService.registerStudentToCourse(email, courseId);
                                                if (success) {
                                                    System.out.println("Successfully registered for the course.");
                                                } else {
                                                    System.out.println("An error occurred while registering for the course. " +
                                                            "Please try again.");
                                                }
                                            }
                                            registeredCourses = studentService.getStudentCourses(email);
                                            System.out.println("You are now registered in the following courses:");
                                            System.out.println("ID : Course Name : Instructor Name");
                                            registeredCourses.forEach(course -> System.out.println
                                                    (course.getCId() + ": " + course.getCName() + " - " + course.getCInstructorName()));
                                            System.out.println("Exiting program. Goodbye!");
                                            return;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid course ID. Please enter a number.");
                                            scanner.nextLine();
                                        } catch (Exception e) {
                                            System.out.println("An error occurred while registering for the course. Please try again.");
                                            e.printStackTrace();
                                            return;
                                        }
                                    }
                                    case "2" -> {
                                        System.out.println("Logged out. Goodbye!");
                                        return;
                                    }
                                    default -> System.out.println("Invalid option. Please try again.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred while retrieving student data. Please try again.");
                            // Log the error message for debugging
                            e.printStackTrace();
                            return;
                        }
                    }
                    case "2" -> {
                        System.out.println("Exiting. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                e.printStackTrace();
                return;
            }
        }
    }
}



