package me.zuif.module3.repository.group;

import me.zuif.module3.config.HibernateSessionFactoryUtil;
import me.zuif.module3.model.Group;
import me.zuif.module3.model.Student;
import me.zuif.module3.repository.CrudRepository;
import me.zuif.module3.repository.grade.GradeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GroupRepository implements CrudRepository<Group>, IGroupRepository {
    private static GroupRepository instance;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();


    public GroupRepository() {
    }

    public static GroupRepository getInstance() {
        if (instance == null) {
            instance = new GroupRepository();
        }
        return instance;
    }

    @Override
    public void save(Group group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Group> groups) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Group group : groups) {
            session.save(group);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Group> findAll() {
        Session session = sessionFactory.openSession();
        List<Group> groups = session.createQuery("select group from Group group", Group.class).getResultList();
        session.close();
        return groups;
    }

    @Override
    public Optional<Group> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Group> group = Optional.ofNullable(session.find(Group.class, id));
        session.close();
        return group;
    }

    @Override
    public boolean update(Group group) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(group);
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
    public List<Group> findGroupsByName(String name) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);

        criteriaQuery.select(root).where(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        TypedQuery<Group> query = session.createQuery(criteriaQuery);
        List<Group> groups = query.getResultList();
        session.close();
        return groups;
    }

    @Override
    public Map<Group, Integer> getGroupsStudentsCount() {


        List<Group> groups = findAll();

        Map<Group, Integer> result = new LinkedHashMap<>();

        for (Group group : groups) {
            int count = group.getStudents().size();
            result.put(group, count);
        }

        return result;
    }

    @Override
    public Map<Group, Double> getGroupsAverageGrade() {
        Map<Group, Double> result = new LinkedHashMap<>();

        GradeRepository repository = GradeRepository.getInstance();

        List<Group> groups = findAll();

        for (Group group : groups) {
            List<Student> students = group.getStudents();
            int studentsCount = students.size();
            double totalAvg = 0;
            for (Student student : students) {
                double avg = repository.getStudentAverageGrade(student);
                totalAvg += avg;
            }
            double avg = totalAvg / studentsCount;
            result.put(group, avg);
        }
        return result;
    }
}
