package me.zuif.module3.repository.grade;

import me.zuif.module3.model.Student;

public interface IGradeRepository {
    double getStudentAverageGrade(Student student);
}
