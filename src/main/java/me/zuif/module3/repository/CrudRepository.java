package me.zuif.module3.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T object);

    void saveAll(List<T> objects);

    List<T> findAll();

    Optional<T> findById(String id);

    boolean update(T object);

    boolean delete(String id);
}
