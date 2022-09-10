package me.zuif.module3.service;

import me.zuif.module3.model.Group;
import me.zuif.module3.repository.CrudRepository;
import me.zuif.module3.repository.group.GroupRepository;

import java.util.List;
import java.util.Map;


public class GroupService extends CrudService<Group> {
    private static GroupService instance;

    private GroupService(CrudRepository repository) {
        super(repository);
    }

    public static GroupService getInstance() {
        if (instance == null) {
            instance = new GroupService(GroupRepository.getInstance());
        }
        return instance;
    }

    public List<Group> findGroupsByName(String name) {
        return ((GroupRepository) getRepository()).findGroupsByName(name);
    }

    public Map<Group, Integer> getGroupsStudentsCount() {
        return ((GroupRepository) getRepository()).getGroupsStudentsCount();
    }

    public Map<Group, Double> getGroupsAverageGrade() {
        return ((GroupRepository) getRepository()).getGroupsAverageGrade();
    }
}
