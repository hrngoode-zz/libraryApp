package com.scottlogic.librarygradproject.repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {
    Optional<T> get(int id);
    List<T> getAll();
    void add(T entity);
    void remove(T entity);
}
