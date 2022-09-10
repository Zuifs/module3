package me.zuif.module3.command;

import me.zuif.module3.model.Student;
import me.zuif.module3.service.StudentService;
import me.zuif.module3.util.Utils;

import java.util.List;

public class FindStudentWithAverageGradeHigherThan implements ICommand {
    @Override
    public void execute() {
        System.out.println("Enter average grade: ");
        String avgStr = SCANNER.nextLine();
        if (Utils.isNumeric(avgStr)) {
            double avg = Double.parseDouble(avgStr);
            StudentService studentService = StudentService.getInstance();
            List<Student> students = studentService.findStudentWithAverageGradeHigherThan(avg);
            System.out.println("Students with average grade higher than " + avg + ":");
            if (students.size() > 0) {
                students.forEach(System.out::println);
            } else {
                System.out.println("Not found");
            }
        } else {
            System.out.println("Wrong input");
        }
    }

}
