package com.scottlogic.librarygradproject.config;

import com.scottlogic.librarygradproject.model.Book;
import com.scottlogic.librarygradproject.model.Reservation;
import com.scottlogic.librarygradproject.repository.BookRepository;
import com.scottlogic.librarygradproject.repository.FilledBookRepository;
import com.scottlogic.librarygradproject.repository.JpaRepo;
import com.scottlogic.librarygradproject.repository.ReservationRepo;
import com.scottlogic.librarygradproject.service.BookService;
import com.scottlogic.librarygradproject.service.ReservationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
public class Config {

    //Book config
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
    BookService bookService(@Qualifier("Empty") BookRepository bookRepository) {
        return new BookService(bookRepository);
    }

    //Reservation config
    @Bean
    List<Reservation> getFilledReservationRepo() {
        return Arrays.asList(
                new Reservation(
                        "one",
                        LocalDate.now(),
                        LocalDate.of(2021, 11, 17),
                        LocalDate.of(2023, 11, 17),
                        UUID.fromString("8eab68d1-b312-4fa3-bdfc-816c63fbc9c2")
                ),
                new Reservation(
                        "two",
                        LocalDate.now(),
                        LocalDate.of(2024, 11, 17),
                        LocalDate.of(2026, 11, 17),
                        UUID.fromString("e848c19b-6165-418a-9950-02e72f5b52e4")
                ),
                new Reservation(
                        "three",
                        LocalDate.now(),
                        LocalDate.of(2025, 11, 17),
                        LocalDate.of(2027, 11, 17),
                        UUID.fromString("c4f3a4d5-7b85-433d-aade-9fa135cd2f6e")
                )
        );
    }

    @Bean
    ReservationService reservationService(ReservationRepo reservationRepo, List<Reservation> reservations){
        return new ReservationService(reservationRepo, reservations);
    }

}
