package com.scottlogic.librarygradproject.repository;

import com.scottlogic.librarygradproject.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BookRepository implements RepositoryInterface<Book> {

    private List<Book> bookCollection = new ArrayList<>();

    @Override
    public Optional<Book> get(UUID id){
        return bookCollection.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    @Override
    public List<Book> getAll() {
        return bookCollection;
    }

    @Override
    public void add(Book entity) {
        bookCollection.add(entity);
    }

    @Override
    public void remove(Book bookToRemove) {
        bookCollection.remove(bookToRemove);
    }
}
