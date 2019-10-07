package com.scottlogic.librarygradproject.config;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.repository.BookRepository;
import com.scottlogic.librarygradproject.repository.FilledBookRepository;
import com.scottlogic.librarygradproject.repository.JpaRepo;
import com.scottlogic.librarygradproject.service.BookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Config {

    @Bean
    List<Book> getFilledBookRepo() {
        return Arrays.asList(
                new Book(
                        "isbn1",
                        "title1",
                        "author1",
                        "publishDate1"
                ),
                new Book(
                        "isbn3",
                        "title3",
                        "author3",
                        "publishDate3"
                ),
                new Book(
                        "isbn2",
                        "title2",
                        "author2",
                        "publishDate2"
                )
        );
    }

    @Bean("Filled")
    FilledBookRepository filledBookRepository(List<Book> books, JpaRepo jpaRepo){
        return new FilledBookRepository(books, jpaRepo);
    }

    @Bean("Empty")
    BookRepository bookRepository(JpaRepo jpaRepo) {
        return new BookRepository(jpaRepo);
    }

    @Bean
    BookService bookService(@Qualifier("Filled") BookRepository bookRepository) {
        return new BookService(bookRepository);
    }

}
