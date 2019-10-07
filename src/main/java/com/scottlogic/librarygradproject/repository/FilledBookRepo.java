package com.scottlogic.librarygradproject.repository;

import com.scottlogic.librarygradproject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("Filled")
public class FilledBookRepo extends BookRepository{

    @Autowired
    public FilledBookRepo(List<Book> books, JpaRepo jpaRepo) {
        setJpaRepo(jpaRepo);
        books.forEach(this::add);
    }

}
