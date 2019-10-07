package com.scottlogic.librarygradproject.service;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.repository.BookRepository;
import com.scottlogic.librarygradproject.repository.FilledBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Component
public class BookService implements ServiceInterface<Book> {

    private BookRepository bookRepository;

    @Autowired
    public BookService(FilledBookRepository bookRepo) {
        bookRepository = bookRepo;
    }

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book get(UUID id) {
        return bookRepository
                .get(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public void remove(UUID id) {
        Book bookToBeRemoved = get(id);
        bookRepository.remove(bookToBeRemoved);
    }

    public void add(Book book) {
        bookRepository.add(book);
    }

    public void put(Book bookToUpdate) {
        bookRepository
                .get(bookToUpdate.getId())
                .ifPresent(book -> remove(book.getId()));
        add(bookToUpdate);
    }
}
