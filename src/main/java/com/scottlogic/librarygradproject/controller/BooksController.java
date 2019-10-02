package com.scottlogic.librarygradproject.controller;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BooksController {

    private BookService bookService;

    @Autowired
    public BooksController(BookService service){
        bookService = service;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public Book get(@PathVariable int id) {
        return bookService.get(id);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        bookService.remove(id);
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public void post(@RequestBody() Book book) {
        bookService.add(book);
    }

    @RequestMapping(value = "/books", method = RequestMethod.PUT)
    public void put(@RequestBody() Book book) {
        bookService.put(book);
    }
}
