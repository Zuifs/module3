package me.zuif.module3.service;

import me.zuif.module3.model.Lecturer;
import me.zuif.module3.repository.CrudRepository;
import me.zuif.module3.repository.lecturer.LecturerRepository;

import java.util.List;

public class LecturerService extends CrudService<Lecturer> {
    private static LecturerService instance;

    private LecturerService(CrudRepository repository) {
        super(repository);
    }

    public static LecturerService getInstance() {
        if (instance == null) {
            instance = new LecturerService(LecturerRepository.getInstance());
        }
        return instance;
    }

    public List<Lecturer> findByFirstAndLastName(String firstName, String lastName) {
        return ((LecturerRepository) getRepository()).findByFirstAndLastName(firstName, lastName);
    }
}
