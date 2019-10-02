package com.scottlogic.librarygradproject.service;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService implements ServiceInterface<Book> {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepo) {
        bookRepository = bookRepo;
    }

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    private Optional<Book> getOptionalBook(int id) {
        return bookRepository
                .getAll()
                .stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    @Override
    public Book get(int id) {
        return bookRepository
                .get(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public void remove(int id) {
        Book bookToBeRemoved = get(id);
        bookRepository.remove(bookToBeRemoved);
    }

    public void add(Book book) {
        bookRepository.add(book);
    }

    public void put(Book bookToUpdate) {
        getOptionalBook(bookToUpdate.getId())
                .ifPresent(book -> remove(book.getId()));
        add(bookToUpdate);
    }
}
