package com.scottlogic.librarygradproject.service;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class BookService implements ServiceInterface<Book> {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepo) {
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
