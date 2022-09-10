package me.zuif.module3.repository.student;

import me.zuif.module3.model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findStudentWithAverageGradeHigherThan(double value);
}
