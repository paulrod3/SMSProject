package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CourseService implements CourseDAO {
    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    @Override
    public List<Course> getAllCourses() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Course> courses = null;
        try {
            transaction = session.beginTransaction();
            Query<Course> query = session.createQuery("FROM Course", Course.class);
            courses = query.getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return courses;
    }
}
