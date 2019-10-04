package com.scottlogic.librarygradproject.service;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.repository.FilledBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService implements ServiceInterface<Book> {

    private FilledBookRepository bookRepository;

    public BookService(FilledBookRepository bookRepo) {
        bookRepository = bookRepo;
    }

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    private Optional<Book> getOptionalBook(UUID id) {
        return bookRepository
                .getAll()
                .stream()
                .filter(book -> book.getId() == id)
                .findFirst();
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
        getOptionalBook(bookToUpdate.getId())
                .ifPresent(book -> remove(book.getId()));
        add(bookToUpdate);
    }
}
