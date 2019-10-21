package com.scottlogic.librarygradproject.controller;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BooksController {

    private BookService bookService;

    @Autowired
    public BooksController(BookService service){
        bookService = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book find(@PathVariable(value = "id") UUID id) {
        return bookService.find(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") UUID id) {
        bookService.remove(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post(@RequestBody() Book book) {
        bookService.add(book);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void put(@RequestBody() Book book) {
        bookService.put(book);
    }
}
