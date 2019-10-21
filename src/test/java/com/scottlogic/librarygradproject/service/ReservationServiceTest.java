package com.scottlogic.librarygradproject.service;

import com.scottlogic.librarygradproject.model.Reservation;
import com.scottlogic.librarygradproject.repository.ReservationRepo;
import com.scottlogic.librarygradproject.utils.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ReservationServiceTest {

    @Mock
    private ReservationRepo reservationRepo ;

    private ReservationService reservationService;

    private UUID uuid = UUID.randomUUID();
    private Reservation reservation = new Reservation("", null, null, null, null);

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(reservationRepo);
    }

    @AfterEach
    void tearDown() {
        reservationRepo.deleteAll();
    }

    @Test
    void get_NoReservationFound_ThrowNoSuchElementException() {

        UUID uuid = UUID.fromString("06ada614-9323-47d5-b262-ee94f3dec068");
        Log returnedLog = reservationService.find(uuid);

        assertFalse(returnedLog.isSuccessful());
    }

    @Test
    void get_ReservationIsFound_ReservationRepositoryFindByIdCalled() {
        //Arrange
        reservationRepo.save(reservation);
        when(reservationRepo.findById(uuid)).thenReturn(java.util.Optional.of(reservation));

        //Act
        reservationService.find(uuid);

        //Assert
        verify(reservationRepo).findById(uuid);
    }

    @Test
    void remove_IdSupplied_ReservationRepoCallsDeleteById() {
        doNothing().when(reservationRepo).deleteById(uuid);

        reservationService.remove(uuid);

        verify(reservationRepo).deleteById(uuid);
    }

    @Test
    void add_DateOutIsAfterDateReturned_ShouldReturnFalse() {
        reservation = new Reservation("", LocalDate.of(2000, 1, 1), LocalDate.of(2001, 1, 1), LocalDate.of(2000, 1, 1), null);

        Log returnedLog = reservationService.add(reservation);

        assertFalse(returnedLog.isSuccessful());
    }

    @Test
    void add_DateOutIsBeforeDateMade_ShouldReturnFalse() {
        reservation = new Reservation("", LocalDate.of(2001, 1, 1), LocalDate.of(2000, 1, 1), LocalDate.of(2002, 1, 1), null);

        Log returnedLog = reservationService.add(reservation);

        assertFalse(returnedLog.isSuccessful());
    }
}




























