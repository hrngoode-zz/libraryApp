package com.scottlogic.librarygradproject.repository;

import com.scottlogic.librarygradproject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FilledBookRepository extends BookRepository{

    @Autowired
    public FilledBookRepository(List<Book> books, JpaRepo jpaRepo) {
        super(jpaRepo);
        setJpaRepo(jpaRepo);
        books.forEach(this::add);
    }

}
