package me.zuif.module3.repository.lecturer;

import me.zuif.module3.config.HibernateSessionFactoryUtil;
import me.zuif.module3.model.Lecturer;
import me.zuif.module3.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class LecturerRepository implements CrudRepository<Lecturer>, ILecturerRepository {
    private static LecturerRepository instance;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();


    public LecturerRepository() {
    }

    public static LecturerRepository getInstance() {
        if (instance == null) {
            instance = new LecturerRepository();
        }
        return instance;
    }

    @Override
    public void save(Lecturer lecturer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(lecturer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Lecturer> lecturers) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Lecturer lecturer : lecturers) {
            session.save(lecturer);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Lecturer> findAll() {
        Session session = sessionFactory.openSession();
        List<Lecturer> lecturers = session.createQuery("select lecturer from Lecturer lecturer", Lecturer.class).getResultList();
        session.close();
        return lecturers;
    }

    @Override
    public Optional<Lecturer> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Lecturer> lecturer = Optional.ofNullable(session.find(Lecturer.class, id));
        session.close();
        return lecturer;
    }

    @Override
    public boolean update(Lecturer lecturer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(lecturer);
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
    public List<Lecturer> findByFirstAndLastName(String firstName, String lastName) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Lecturer> criteriaQuery = criteriaBuilder.createQuery(Lecturer.class);
        Root<Lecturer> root = criteriaQuery.from(Lecturer.class);

        criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("firstName"), firstName),
                criteriaBuilder.equal(root.get("lastName"), lastName)));
        TypedQuery<Lecturer> query = session.createQuery(criteriaQuery);
        List<Lecturer> result = query.getResultList();
        session.close();
        return result;
    }
}
