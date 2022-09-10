package me.zuif.module3.command;

public enum Commands {
    FIND_GROUPS_BY_NAME("Find groups by name", new FindGroupsByName()),
    FIND_LECTURER_BY_FULL_NAME("Find lecturer by full name", new FindLecturerByFullName()),
    FIND_STUDENT_WITH_AVERAGE_GRADE_HIGHER_THAN("Find student with average grade higher than", new FindStudentWithAverageGradeHigherThan()),
    GET_GROUPS_AVERAGE_GRADE("Get groups average grade", new GetGroupsAverageGrade()),
    GET_GROUPS_STUDENTS_COUNT("Get groups students count", new GetGroupsStudentsCount()),
    GET_STUDENTS_PERFORMANCE_STATISTIC("Get subject with best and worst performance", new GetStudentsPerformanceStatistic()),
    EXIT("Exit", null);
    private final String name;
    private final ICommand command;

    Commands(String name, ICommand command) {
        this.name = name;
        this.command = command;
    }
}
