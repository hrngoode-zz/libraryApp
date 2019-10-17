package com.scottlogic.librarygradproject.repository;


import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T, S>{
    Optional<T> find(S id);
    List<T> findAll();
    void add(T entity);
    void remove(T entity);
}
