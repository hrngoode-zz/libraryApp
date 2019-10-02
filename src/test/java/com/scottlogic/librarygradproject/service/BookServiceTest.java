package com.scottlogic.librarygradproject.service;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.repository.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    private BookRepository mockRepo;
    private BookService bookService;

    @Before
    public void setUp() throws Exception {
        mockRepo = mock(BookRepository.class);
        bookService = new BookService(mockRepo);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void new_BookService_Is_Empty() {

        // Act
        List<Book> books = bookService.getAll();

        // Assert
        assertTrue(books.isEmpty());
    }

    @Test
    public void getAll_Calls_Repo_GetAll() {
        bookService.getAll();
        verify(mockRepo).getAll();
    }

    @Test
    public void get_Calls_Repo_Get()  {

        Book newBook = new Book();
        Optional<Book> optionalBook = Optional.of(newBook);

        when(mockRepo.get(1)).thenReturn(optionalBook);

        bookService.get(1);
        verify(mockRepo).get(1);
    }



    @Test
    public void remove() {
    }

    @Test
    public void add() {
    }

    @Test
    public void put() {
    }
}


