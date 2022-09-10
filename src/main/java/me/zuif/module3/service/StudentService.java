package me.zuif.module3.service;

import me.zuif.module3.model.Student;
import me.zuif.module3.repository.CrudRepository;
import me.zuif.module3.repository.student.StudentRepository;

import java.util.List;

public class StudentService extends CrudService<Student> {
    private static StudentService instance;

    private StudentService(CrudRepository repository) {
        super(repository);
    }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService(StudentRepository.getInstance());
        }
        return instance;
    }

    public List<Student> findStudentWithAverageGradeHigherThan(double value) {
        return ((StudentRepository) getRepository()).findStudentWithAverageGradeHigherThan(value);
    }
}
