package com.scottlogic.librarygradproject.repository;

import com.scottlogic.librarygradproject.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BookRepositoryTest {

    @Autowired
    @Qualifier("Empty")
    BookRepository repo;

    private Book book1 = new Book(
                "isbn1",
                        "title1",
                        "author1",
                        "publishDate1",
                UUID.fromString("c1be0406-ba3e-436f-9a6b-aea6080e0d00")

    );
    private Book book2 = new Book(
                "isbn2",
                        "title2",
                        "author2",
                        "publishDate2",
                     UUID.fromString("c1be0406-ba3e-436f-9a6b-aea6080e0d01")
    );
    private Book book3 = new Book(
                    "isbn3",
                            "title3",
                            "author3",
                            "publishDate3",
                    UUID.fromString("c1be0406-ba3e-436f-9a6b-aea6080e0d02")
    );

    @Before
    public void setUp(){
        repo.removeAll();
    }

    @After
    public void tearDown(){
    }
    

    @Test
    public void get_Returns_Specific_Books() {

        // Arrange
        repo.add(book1);
        repo.add(book2);

        // Act
        Book book = repo.find(book2.getId()).get();

        // Assert
        assertEquals(book2, book);
    }

    @Test
    public void add_ToEmptyRepo_IncreaseSizeOfRepoToOneAndAddsCorrectBook() {
        repo.add(book1);

        Book foundBook = repo.find(book1.getId()).get();
        assertThat(repo.findAll().size(), is(1));
        assertThat(foundBook, is(book1));
    }

    @Test
    public void delete_OnRepoWithThreeBooksInIt_ReducesRepoSizeToTwo() {

        // Arrange
        List<Book> expectedList = Arrays.asList(book2, book3);

        repo.add(book1);
        repo.add(book2);
        repo.add(book3);

        // Act
        repo.remove(book1);
        List<Book> foundBooks = repo.findAll();

        // Assert
        assertThat(foundBooks.size(), is(2));
        assertThat(foundBooks, is(expectedList));
    }
}
