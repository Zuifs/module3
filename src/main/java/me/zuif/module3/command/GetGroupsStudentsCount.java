package me.zuif.module3.command;

import me.zuif.module3.model.Group;
import me.zuif.module3.service.GroupService;

import java.util.Map;

public class GetGroupsStudentsCount implements ICommand {
    @Override
    public void execute() {
        GroupService groupService = GroupService.getInstance();
        System.out.println("Groups students count: ");
        Map<Group, Integer> groupStudentsCount = groupService.getGroupsStudentsCount();
        for (Map.Entry<Group, Integer> entry : groupStudentsCount.entrySet()) {
            System.out.println("Group: " + entry.getKey() + " students count: " + entry.getValue());
        }
    }
}
