package com.scottlogic.librarygradproject.repository;

import com.scottlogic.librarygradproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRepo extends JpaRepository<Book, Long> {
}
