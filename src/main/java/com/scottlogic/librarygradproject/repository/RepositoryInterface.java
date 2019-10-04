package com.scottlogic.librarygradproject.repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryInterface<T>{
    Optional<T> get(UUID id);
    List<T> getAll();
    void add(T entity);
    void remove(T entity);
}
