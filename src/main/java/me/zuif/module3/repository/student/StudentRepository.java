package me.zuif.module3.repository.student;

import me.zuif.module3.config.HibernateSessionFactoryUtil;
import me.zuif.module3.model.Student;
import me.zuif.module3.repository.CrudRepository;
import me.zuif.module3.repository.grade.GradeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository implements CrudRepository<Student>, IStudentRepository {
    private static StudentRepository instance;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();


    public StudentRepository() {
    }

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    @Override
    public void save(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Student> students) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Student student : students) {
            session.save(student);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        List<Student> students = session.createQuery("select student from Student student", Student.class).getResultList();
        session.close();
        return students;
    }

    @Override
    public Optional<Student> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Student> student = Optional.ofNullable(session.find(Student.class, id));
        session.close();
        return student;
    }

    @Override
    public boolean update(Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(student);
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
    public List<Student> findStudentWithAverageGradeHigherThan(double higherThan) {
        List<Student> result = new ArrayList<>();
        GradeRepository gradeRepository = GradeRepository.getInstance();

        List<Student> studentsAll = findAll();

        for (Student student : studentsAll) {
            double avg = gradeRepository.getStudentAverageGrade(student);
            if (avg > higherThan) {
                result.add(student);
            }
        }
        return result;
    }
}
