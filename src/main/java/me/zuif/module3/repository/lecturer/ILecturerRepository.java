package me.zuif.module3.repository.lecturer;

import me.zuif.module3.model.Lecturer;

import java.util.List;

public interface ILecturerRepository {
    List<Lecturer> findByFirstAndLastName(String firstName, String lastName);

}
