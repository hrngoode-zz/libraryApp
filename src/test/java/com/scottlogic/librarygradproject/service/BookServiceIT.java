package com.scottlogic.librarygradproject.service;


import com.scottlogic.librarygradproject.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookServiceIT {

    @Autowired
    private BookService bookService;

    @Test
    public void remove_With_Id_Calls_Repo_Remove() {

        //Arrange
        Book newBook1 = new Book();
        Book newBook2 = new Book();
        Book newBook3 = new Book();

        bookService.add(newBook1);
        bookService.add(newBook2);
        bookService.add(newBook3);

        //Act
        bookService.remove(newBook1.getId());
        List<Book> books = bookService.getAll();

        //Assert
        assertThat(books, hasSize(2));
        assertThat(books.toArray(), arrayContaining(newBook2, newBook3));
    }

}
