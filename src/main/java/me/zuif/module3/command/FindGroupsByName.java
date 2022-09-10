package me.zuif.module3.command;

import me.zuif.module3.model.Group;
import me.zuif.module3.service.GroupService;

import java.util.List;

public class FindGroupsByName implements ICommand {

    @Override
    public void execute() {
        System.out.println("Enter group name");
        String name = SCANNER.nextLine();
        GroupService groupService = GroupService.getInstance();
        List<Group> groups = groupService.findGroupsByName(name);
        if (groups.size() > 0) {
            System.out.println("Found groups: ");
            groups.forEach(System.out::println);
        } else {
            System.out.println("Groups not found");
        }
    }
}
