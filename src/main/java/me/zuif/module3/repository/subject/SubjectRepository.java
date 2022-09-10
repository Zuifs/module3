package me.zuif.module3.repository.subject;

import me.zuif.module3.config.HibernateSessionFactoryUtil;
import me.zuif.module3.model.Grade;
import me.zuif.module3.model.Subject;
import me.zuif.module3.repository.CrudRepository;
import me.zuif.module3.repository.grade.GradeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;
import java.util.stream.Collectors;

public class SubjectRepository implements CrudRepository<Subject>, ISubjectRepository {
    private static SubjectRepository instance;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();


    public SubjectRepository() {
    }

    public static SubjectRepository getInstance() {
        if (instance == null) {
            instance = new SubjectRepository();
        }
        return instance;
    }

    @Override
    public void save(Subject subject) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(subject);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Subject> subjects) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Subject subject : subjects) {
            session.save(subject);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Subject> findAll() {
        Session session = sessionFactory.openSession();
        List<Subject> subjects = session.createQuery("select subject from Subject subject", Subject.class).getResultList();
        session.close();
        return subjects;
    }

    @Override
    public Optional<Subject> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Subject> subject = Optional.ofNullable(session.find(Subject.class, id));
        session.close();
        return subject;
    }

    @Override
    public boolean update(Subject subject) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(subject);
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
    public Map<Subject, Double> getStudentsPerformanceStatistic() {
        GradeRepository repository = GradeRepository.getInstance();

        List<Grade> grades = repository.findAll();

        Map<Subject, Integer> subject_studentsCount = new HashMap<>();
        Map<Subject, Integer> subject_totalGrade = new HashMap<>();

        for (Grade grade : grades) {
            Subject subject = grade.getSubject();
            int value = grade.getValue();
            int studentCount = grade.getStudents().size();

            subject_totalGrade.computeIfPresent(subject, (key, totalGrade) -> totalGrade + value);
            subject_totalGrade.putIfAbsent(subject, value);

            subject_studentsCount.computeIfPresent(subject, (key, count) -> count + studentCount);
            subject_studentsCount.putIfAbsent(subject, studentCount);
        }
        Map<Subject, Double> result = new LinkedHashMap<>();
        for (Subject subject : subject_totalGrade.keySet()) {
            int totalGrade = subject_totalGrade.get(subject);
            int studentCount = subject_studentsCount.get(subject);

            double avg = (double) totalGrade / (double) studentCount;
            result.put(subject, avg);
        }
        result = result.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey,
                Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return result;
    }
}
