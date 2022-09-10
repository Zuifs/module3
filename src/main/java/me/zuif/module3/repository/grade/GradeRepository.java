package me.zuif.module3.repository.grade;

import me.zuif.module3.config.HibernateSessionFactoryUtil;
import me.zuif.module3.model.Grade;
import me.zuif.module3.model.Student;
import me.zuif.module3.model.Subject;
import me.zuif.module3.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradeRepository implements CrudRepository<Grade>, IGradeRepository {
    private static GradeRepository instance;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();


    public GradeRepository() {
    }

    public static GradeRepository getInstance() {
        if (instance == null) {
            instance = new GradeRepository();
        }
        return instance;
    }

    @Override
    public void save(Grade grade) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(grade);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Grade> grades) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Grade grade : grades) {
            session.save(grade);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Grade> findAll() {
        Session session = sessionFactory.openSession();
        List<Grade> grades = session.createQuery("select grade from Grade grade", Grade.class).getResultList();
        session.close();
        return grades;
    }

    @Override
    public Optional<Grade> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Grade> grade = Optional.ofNullable(session.find(Grade.class, id));
        session.close();
        return grade;
    }

    @Override
    public boolean update(Grade grade) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(grade);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(findById(id).get());
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public double getStudentAverageGrade(Student target) {
        int total = 0;
        List<Subject> subjectList = new ArrayList<>();
        List<Grade> grades = findAll();
        for (Grade grade : grades) {
            Subject subject = grade.getSubject();
            int value = grade.getValue();
            List<Student> students = grade.getStudents();
            if (students.contains(target)) {
                subjectList.add(subject);
                total += value;
            }
        }
        int subjectCount = subjectList.size();
        double avg = (double) total / (double) subjectCount;
        return avg;
    }
}
