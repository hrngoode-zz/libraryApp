package com.scottlogic.librarygradproject.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ReservationTest {

    private UUID uuid = UUID.randomUUID();
    private String partyName = "test";
    private LocalDate dateOut = LocalDate.of(2003, 11, 17);
    private LocalDate dateReturned = LocalDate.of(2003, 11, 18);
    private LocalDate dateMade = LocalDate.of(2019, 10, 10);
    private UUID bookId = UUID.randomUUID();


    private Reservation reservation;

    @BeforeEach
    void setUp() {
        reservation = new Reservation(uuid, partyName, dateMade, dateOut, dateReturned, bookId);
    }

    @AfterEach
    void tearDown() {
        reservation = null;
    }

    @Test
    void getId() {
        assertThat(reservation.getId(), is(uuid));
    }

    @Test
    void getPartyName() {
        assertThat(reservation.getPartyName(), is(partyName));
    }

    @Test
    void setPartyName() {
        String newName = "new";
        reservation.setPartyName(newName);
        assertThat(reservation.getPartyName(), is(newName));
    }

    @Test
    void getDateMade() {
        assertThat(reservation.getDateMade(), is(dateMade));
    }

    @Test
    void setDateMade() {
        LocalDate newDate = LocalDate.now();
        reservation.setDateMade(newDate);
        assertThat(reservation.getDateMade(),is(newDate));
    }

    @Test
    void getDateOut() {
        assertThat(reservation.getDateOut(), is(dateOut));
    }

    @Test
    void setDateOut() {
        LocalDate newDate = LocalDate.now();
        reservation.setDateOut(newDate);
        assertThat(reservation.getDateOut(), is(newDate));
    }

    @Test
    void getDateReturned() {
        assertThat(reservation.getDateReturned(), is(dateReturned));
    }

    @Test
    void setDateReturned() {
        LocalDate newDate = LocalDate.now();
        reservation.setDateReturned(newDate);
        assertThat(reservation.getDateReturned(), is(newDate));
    }

    @Test
    void getBookId() {
        assertThat(reservation.getBookId(), is(bookId));
    }
}