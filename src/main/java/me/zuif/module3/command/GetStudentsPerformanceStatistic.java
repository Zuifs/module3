package me.zuif.module3.command;

import me.zuif.module3.model.Subject;
import me.zuif.module3.service.SubjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetStudentsPerformanceStatistic implements ICommand {
    @Override
    public void execute() {
        SubjectService subjectService = SubjectService.getInstance();
        Map<Subject, Double> statistic = subjectService.getStudentsPerformanceStatistic();
        List<Map.Entry<Subject, Double>> list = new ArrayList<>(statistic.entrySet());
        System.out.println("Best performance subject: ");
        System.out.println(list.get(list.size() - 1).getKey() + " average grade: " + list.get(list.size() - 1).getValue());
        System.out.println();
        System.out.println("Worst performance subject: ");
        System.out.println(list.get(0).getKey() + " average grade: " + list.get(0).getValue());

    }
}
