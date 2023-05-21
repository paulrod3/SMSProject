package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.List;

public class StudentService implements StudentDAO {
    SessionFactory sessionFactory = new Configuration().configure
            ("hibernate.cfg.xml").buildSessionFactory();

    @Override
    public List<Student> getAllStudents() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Student> students = null;
        try {
            tx = session.beginTransaction();
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            students = query.getResultList();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return students;
    }

    @Override
    public Student getStudentByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Student student = null;
        try {
            tx = session.beginTransaction();
            student = session.get(Student.class, email);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student;
    }

    @Override
    public boolean validateStudent(String sEmail, String password) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Student student = null;
        try {
            tx = session.beginTransaction();
            student = session.get(Student.class, sEmail);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student != null && student.getSPass().equals(password);
    }

    @Override
    public boolean registerStudentToCourse(String sEmail, int cId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, sEmail);
            Course course = session.get(Course.class, cId);
            if (student != null && course != null) {
                student.getSCourses().add(course);
                session.merge(student);
                success = true;
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return success;
    }

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Course> courses = null;
        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, sEmail);
            courses = student != null ? student.getSCourses() : null;
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return courses;
    }
}