package com.scottlogic.librarygradproject.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private UUID uuid = UUID.randomUUID();
    private String partyName = "test";
    private LocalDate dateOut = LocalDate.parse("2003-11-17");
    private LocalDate dateReturned = LocalDate.parse("2003-11-18");
    private LocalDate dateMade = LocalDate.parse("2019-10-10");
    private UUID bookId = UUID.randomUUID();


    private Reservation one = new Reservation(uuid, partyName, dateMade, dateOut, dateReturned, bookId);
    private Reservation two;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void equals_DuplicateReservationObjects_ShouldBeEqual() {
        two = new Reservation(uuid, partyName, dateMade, dateOut, dateReturned, bookId);
        assertEquals(one, two);
    }

    @Test
    void equals_ReservationObjectsHaveDifferentIds_ShouldNotEqual() {
        two = new Reservation(
                UUID.randomUUID(),
                partyName,
                dateMade,
                dateOut,
                dateReturned,
                bookId
        );
        assertNotEquals(one, two);
    }

    @Test
    void equals_ReservationObjectsHaveDifferentNames_ShouldNotEqual() {
        two = new Reservation(
                uuid,
                "not test",
                dateMade,
                dateOut,
                dateReturned,
                bookId
        );
        assertNotEquals(one, two);
    }

    @Test
    void equals_ReservationObjectsHaveDifferentDateMade_ShouldNotEqual() {
        two = new Reservation(
                uuid,
                partyName,
                LocalDate.parse("2000-01-01"),
                dateOut,
                dateReturned,
                bookId
        );
        assertNotEquals(one, two);
    }

    @Test
    void equals_ReservationObjectsHaveDifferentDateOut_ShouldNotEqual() {
        two = new Reservation(
                uuid,
                partyName,
                dateMade,
                LocalDate.parse("2000-01-01"),
                dateReturned,
                bookId
        );
        assertNotEquals(one, two);
    }

    @Test
    void equals_ReservationObjectsHaveDifferentDateReturned_ShouldNotEqual() {
        two = new Reservation(
                uuid,
                partyName,
                dateMade,
                dateOut,
                LocalDate.parse("2000-01-01"),
                bookId
        );
        assertNotEquals(one, two);
    }

    @Test
    void equals_ReservationObjectsHaveDifferentBookId_ShouldNotEqual() {
        two = new Reservation(
                uuid,
                partyName,
                dateMade,
                dateOut,
                dateReturned,
                UUID.randomUUID()
        );
        assertNotEquals(one, two);
    }
}