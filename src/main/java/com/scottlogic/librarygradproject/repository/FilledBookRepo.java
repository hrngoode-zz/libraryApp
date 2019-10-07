package com.scottlogic.librarygradproject.repository;

import com.scottlogic.librarygradproject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Filled")
public class FilledBookRepo implements RepositoryInterface<Book>{

    private JpaRepo jpaRepo;

    @Autowired
    public FilledBookRepo(List<Book> books, JpaRepo jpaRepo) {
        this.jpaRepo = jpaRepo;
        books.forEach(jpaRepo::save);
    }

    @Override
    public Optional<Book> get(UUID id) {
        return jpaRepo.findById(id);
    }

    @Override
    public List<Book> getAll() {
        return jpaRepo.findAll();
    }

    @Override
    public void add(Book entity) {
        jpaRepo.save(entity);
    }

    @Override
    public void remove(Book entity) {
        jpaRepo.delete(entity);
    }
}
