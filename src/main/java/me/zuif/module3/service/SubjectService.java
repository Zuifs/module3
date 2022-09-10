package me.zuif.module3.service;

import me.zuif.module3.model.Subject;
import me.zuif.module3.repository.CrudRepository;
import me.zuif.module3.repository.subject.SubjectRepository;

import java.util.Map;

public class SubjectService extends CrudService<Subject> {
    private static SubjectService instance;

    private SubjectService(CrudRepository repository) {
        super(repository);
    }

    public static SubjectService getInstance() {
        if (instance == null) {
            instance = new SubjectService(SubjectRepository.getInstance());
        }
        return instance;
    }

    public Map<Subject, Double> getStudentsPerformanceStatistic() {
        return ((SubjectRepository) getRepository()).getStudentsPerformanceStatistic();
    }
}
