package me.zuif.module3.controller;

import me.zuif.module3.command.*;
import me.zuif.module3.util.UserInputUtil;
import me.zuif.module3.util.Utils;

import java.util.List;

public class Controller implements Runnable {

    @Override
    public void run() {
        final Commands[] values = Commands.values();
        final List<String> names = Utils.getNamesOfEnum(values);

        boolean exit = false;
        do {
            int commandIndex = UserInputUtil.getUserInput(values.length, names);
            switch (values[commandIndex]) {
                case FIND_GROUPS_BY_NAME -> new FindGroupsByName().execute();
                case FIND_LECTURER_BY_FULL_NAME -> new FindLecturerByFullName().execute();
                case FIND_STUDENT_WITH_AVERAGE_GRADE_HIGHER_THAN ->
                        new FindStudentWithAverageGradeHigherThan().execute();
                case GET_GROUPS_AVERAGE_GRADE -> new GetGroupsAverageGrade().execute();
                case GET_GROUPS_STUDENTS_COUNT -> new GetGroupsStudentsCount().execute();
                case GET_STUDENTS_PERFORMANCE_STATISTIC -> new GetStudentsPerformanceStatistic().execute();
                case EXIT -> exit = true;
            }

        } while (!exit);

    }
}
