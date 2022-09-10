package me.zuif.module3.repository.group;

import me.zuif.module3.model.Group;

import java.util.List;
import java.util.Map;

public interface IGroupRepository {
    List<Group> findGroupsByName(String name);

    Map<Group, Integer> getGroupsStudentsCount();

    Map<Group, Double> getGroupsAverageGrade();
}
