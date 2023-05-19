package jpa.dao;

import jpa.entitymodels.Student;
import jpa.entitymodels.Course;

import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents();

    Student getStudentByEmail(String sEmail);

    boolean validateStudent(String sEmail, String sPass);

    boolean registerStudentToCourse(String sEmail, int cId);

    List<Course> getStudentCourses(String sEmail);
}
