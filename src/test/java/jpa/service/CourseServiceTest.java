package jpa.service;

import jpa.entitymodels.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {
    private CourseService courseService;
    @BeforeEach
    public void setup() {
        courseService = new CourseService();
    }

    @Test
    public void testGetAllCourses() {
        // Call the getAllCourses() method
        List<Course> courses = courseService.getAllCourses();

        // Assert that the returned list is not null and contains at least one course
        Assertions.assertNotNull(courses);
        Assertions.assertFalse(courses.isEmpty());
    }

}