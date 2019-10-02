package com.scottlogic.librarygradproject;

import com.scottlogic.librarygradproject.controller.BooksController;
import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.service.BookService;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BooksControllerTest {

    @Test
    public void getAll_Calls_Repo_GetAll() {

        // Arrange
        BookService mockService = mock(BookService.class);
        BooksController controller = new BooksController(mockService);

        // Act
        controller.getAll();

        // Assert
        verify(mockService).getAll();
    }

    @Test
    public void get_With_Id_Calls_Service_Get() {

        // Arrange
        int id = 1;
        BookService mockService = mock(BookService.class);
        BooksController controller = new BooksController(mockService);

        // Act
        controller.get(id);

        // Assert
        verify(mockService).get(id);
    }

    @Test
    public void post_With_Book_Calls_Service_Add() {

        // Arrange
        BookService mockService = mock(BookService.class);
        BooksController controller = new BooksController(mockService);

        Book newBook = new Book();

        // Act
        controller.post(newBook);

        // Assert
        verify(mockService).add(newBook);
    }

    @Test
    public void delete_With_Id_Calls_Service_Remove() {

        // Arrange
        int id = 1;
        BookService mockService = mock(BookService.class);
        BooksController controller = new BooksController(mockService);

        // Act
        controller.delete(id);

        // Assert
        verify(mockService).remove(id);
    }
}
