package com.scottlogic.librarygradproject.config;

import com.scottlogic.librarygradproject.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Config {

    @Bean
    List<Book> filledBookRepo() {
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

}
