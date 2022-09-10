package me.zuif.module3.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zuif.module3.repository.CrudRepository;

import java.util.List;

@AllArgsConstructor
@Getter
public abstract class CrudService<T> {
    private final CrudRepository<T> repository;

    public void save(T target) {
        repository.save(target);
    }

    protected CrudRepository<T> getRepository() {
        return repository;
    }

    public void update(T target) {
        repository.update(target);
    }

    public T findById(String id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public void printAll() {
        for (T target : repository.findAll()) {
            System.out.println(target);
        }
    }
}
