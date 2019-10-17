package com.scottlogic.librarygradproject.controller;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.service.BookService;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class BooksControllerTest {

    @Test
    public void getAll_Calls_Repo_GetAll() {

        // Arrange
        BookService mockService = mock(BookService.class);
        BooksController controller = new BooksController(mockService);

        // Act
        controller.findAll();

        // Assert
        verify(mockService).findAll();
    }

    @Test
    public void get_With_Id_Calls_Service_Get() {

        // Arrange
        UUID id = new UUID(1,1);
        BookService mockService = mock(BookService.class);
        BooksController controller = new BooksController(mockService);

        // Act
        controller.find(id);

        // Assert
        verify(mockService).find(id);
    }

    @Test
    public void post_With_Book_Calls_Service_Add() {

        // Arrange
        BookService mockService = mock(BookService.class);
        BooksController controller = new BooksController(mockService);

        Book newBook = new Book(
                "",
                "",
                "",
                "",
                UUID.fromString("06ada614-9323-47d5-b262-ee94f3dec068")
        );

        // Act
        controller.post(newBook);

        // Assert
        verify(mockService).add(newBook);
    }

    @Test
    public void delete_With_Id_Calls_Service_Remove() {

        // Arrange
        UUID id = new UUID(1,1);
        BookService mockService = mock(BookService.class);
        BooksController controller = new BooksController(mockService);

        // Act
        controller.delete(id);

        // Assert
        verify(mockService).remove(id);
    }
}
