package me.zuif.module3.service;

import me.zuif.module3.model.Grade;
import me.zuif.module3.model.Student;
import me.zuif.module3.repository.CrudRepository;
import me.zuif.module3.repository.grade.GradeRepository;

public class GradeService extends CrudService<Grade> {
    private static GradeService instance;

    private GradeService(CrudRepository repository) {
        super(repository);
    }

    public static GradeService getInstance() {
        if (instance == null) {
            instance = new GradeService(GradeRepository.getInstance());
        }
        return instance;
    }

    public double getStudentAverageGrade(Student student) {
        return ((GradeRepository) getRepository()).getStudentAverageGrade(student);
    }
}
