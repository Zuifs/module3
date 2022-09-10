package me.zuif.module3.command;

import me.zuif.module3.model.Lecturer;
import me.zuif.module3.service.LecturerService;

import java.util.List;

public class FindLecturerByFullName implements ICommand {
    @Override
    public void execute() {
        System.out.println("Enter lecturer first name");
        String firstName = SCANNER.nextLine();
        System.out.println("Enter lecturer last name");
        String lastName = SCANNER.nextLine();
        LecturerService lecturerService = LecturerService.getInstance();
        List<Lecturer> lecturers = lecturerService.findByFirstAndLastName(firstName, lastName);

        if (lecturers.size() > 0) {
            System.out.println("Found: ");
            lecturers.forEach(System.out::println);
        } else {
            System.out.println("Not found");
        }
    }
}
