package com.scottlogic.librarygradproject.service;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.repository.FilledBookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    private BookService bookService;
    private FilledBookRepository mockRepo;

    @Before
    public void setUp() {
        mockRepo = mock(FilledBookRepository.class);
        bookService = new BookService(mockRepo);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void new_BookService_Is_Empty() {

        // Act
        List<Book> books = bookService.findAll();

        // Assert
        assertTrue(books.isEmpty());
    }

    @Test
    public void getAll_Calls_Repo_GetAll() {
        bookService.findAll();
        verify(mockRepo).findAll();
    }

    @Test
    public void get_Calls_Repo_Get()  {

        Book newBook = new Book(
                "",
                "",
                "",
                ""
        );
        UUID id = new UUID(1,1);
        Optional<Book> optionalBook = Optional.of(newBook);

        when(mockRepo.find(id)).thenReturn(optionalBook);

        bookService.find(id);
        verify(mockRepo).find(id);
    }

    public void remove() {

    }

    public void add() {
    }

    public void put() {
    }
}
