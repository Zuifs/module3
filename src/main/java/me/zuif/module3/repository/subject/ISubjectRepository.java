package me.zuif.module3.repository.subject;

import me.zuif.module3.model.Subject;

import java.util.Map;

public interface ISubjectRepository {
    Map<Subject, Double> getStudentsPerformanceStatistic();
}
