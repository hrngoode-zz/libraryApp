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

    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void setUp(){
        List<Book> all = repo.getAll();
        all.forEach(repo::remove);
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
        List<Book> all = repo.getAll();
        all.forEach(repo::remove);
    }


    @Test
    public void new_BookRepository_Is_Empty() {
        // Act
        List<Book> books = repo.getAll();

        // Assert
        assertTrue(books.isEmpty());
    }

    @Test
    public void add_ToEmptyRepo_IncreaseSizeOfRepoToOne() {
        // Act
        repo.add(book1);
        List<Book> books = repo.getAll();

        // Assert
        assertThat(books.size(), is(1));
    }

    @Test
    public void getAll_CalledOnRepoWithTwoBooks_ReturnListOfSizeTwo() {

        // Arrange
        repo.add(book1);
        repo.add(book2);

        // Act
        List<Book> books = repo.getAll();

        // Assert
        assertThat(books.size(), is(2));
    }

    @Test
    public void get_Returns_Specific_Books() {

        // Arrange
        repo.add(book1);
        repo.add(book2);

        // Act
        Book book = repo.get(book2.getId()).get();

        // Assert
        assertEquals(book2, book);
    }

    @Test
    public void delete_OnRepoWithThreeBooksInIt_ReducesRepoSizeToTwo() {

        // Arrange
        repo.add(book1);
        repo.add(book2);
        repo.add(book3);

        // Act
        repo.remove(book1);
        List<Book> books = repo.getAll();

        // Assert
        assertThat(books.size(), is(2));
    }
}
