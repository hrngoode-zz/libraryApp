package com.scottlogic.librarygradproject.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private String partyName = "test";
    private LocalDate dateOut = LocalDate.parse("2003-11-17");
    private LocalDate dateReturned = LocalDate.parse("2003-11-18");
    private LocalDate dateMade = LocalDate.parse("2019-10-10");
    private UUID bookId = UUID.randomUUID();


    private Reservation one = new Reservation(partyName, dateMade, dateOut, dateReturned, bookId);
    private Reservation two;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void equals_DuplicateReservationObjects_ShouldBeEqual() {
        two = one;
        assertEquals(one, two);
    }

    @Test
    void equals_ReservationObjectsHaveDifferentIds_ShouldNotEqual() {
        two = new Reservation(
                partyName,
                dateMade,
                dateOut,
                dateReturned,
                bookId
        );
        assertNotEquals(one, two);
    }

}