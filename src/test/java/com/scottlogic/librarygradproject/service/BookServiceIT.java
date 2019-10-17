package com.scottlogic.librarygradproject.service;


import com.scottlogic.librarygradproject.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookServiceIT {

    private Book book1;
    private Book book2;
    private Book book3;

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() {
        List<Book> books = bookService.getAll();
        books.forEach(book -> bookService.remove(book.getId()));
        book1 = new Book(
                "isbn1",
                "title1",
                "author1",
                "publishDate1",
                UUID.randomUUID()
        );
        book2 = new Book(
                "isbn2",
                "title2",
                "author2",
                "publishDate2",
                UUID.randomUUID()
        );
        book3 = new Book(
                "isbn3",
                "title3",
                "author3",
                "publishDate3",
                UUID.randomUUID()
        );
    }

    @After
    public void tearDown(){
    }



    @Test
    public void remove_With_Id_Calls_Repo_Remove() {

        //Arrange
        bookService.add(book1);
        bookService.add(book2);
        bookService.add(book3);

        //Act
        bookService.remove(book1.getId());
        List<Book> books = bookService.getAll();

        //Assert
        assertThat(books, hasSize(2));
        assertThat(books.toArray(), arrayContaining(book2, book3));
    }

}
