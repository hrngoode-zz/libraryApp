package com.scottlogic.librarygradproject.repository;

import com.scottlogic.librarygradproject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BookRepository implements RepositoryInterface<Book, UUID> {

    @Autowired
    private JpaRepo jpaRepo;

    public BookRepository() {}

    @Autowired
    public BookRepository(JpaRepo jpaRepo) {
        setJpaRepo(jpaRepo);
    }

    @Override
    public Optional<Book> find(UUID id) {
        return jpaRepo.findById(id);
    }

    @Override
    public List<Book> findAll() {
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

    void setJpaRepo(JpaRepo jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    public void removeAll() {
        jpaRepo.deleteAll();
    }
}
