package com.scottlogic.librarygradproject.repository;

import com.scottlogic.librarygradproject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilledBookRepository extends BookRepository {

    @Autowired
    public FilledBookRepository(List<Book> filledBookCollection) {
        filledBookCollection
                .forEach(super::add);
    }
}
