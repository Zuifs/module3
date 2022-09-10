package me.zuif.module3.command;

import me.zuif.module3.model.Group;
import me.zuif.module3.service.GroupService;

import java.util.Map;

public class GetGroupsAverageGrade implements ICommand {
    @Override
    public void execute() {
        GroupService groupService = GroupService.getInstance();
        System.out.println("Groups average grade: ");
        Map<Group, Double> groupAverageGrade = groupService.getGroupsAverageGrade();
        for (Map.Entry<Group, Double> entry : groupAverageGrade.entrySet()) {
            System.out.println("Group: " + entry.getKey() + " average grade: " + entry.getValue());
        }
    }
}
