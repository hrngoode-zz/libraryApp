package com.scottlogic.librarygradproject.service;

import com.scottlogic.librarygradproject.model.Reservation;
import com.scottlogic.librarygradproject.repository.ReservationRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ReservationServiceTest {

    @Mock
    private ReservationRepo reservationRepo ;

    private ReservationService reservationService;

    private UUID uuid = UUID.randomUUID();
    private Reservation reservation = new Reservation(uuid, "", null, null, null, null);;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(reservationRepo);
    }

    @AfterEach
    void tearDown() {
        reservationService = null;
        reservationRepo.deleteAll();

    }

    @Test
    void get_NoReservationFound_ThrowNoSuchElementException() {
        UUID uuid = UUID.randomUUID();
        assertThrows(NoSuchElementException.class, () -> reservationService.get(uuid));
    }

    @Test
    void get_ReservationIsFound_ReservationRepositoryFindByIdCalled() {
        //Arrange
        reservationRepo.save(reservation);
        when(reservationRepo.findById(uuid)).thenReturn(java.util.Optional.of(reservation));

        //Act
        reservationService.get(uuid);

        //Assert
        verify(reservationRepo).findById(uuid);
    }

    @Test
    void getAll() {
        //Arrange
        List<Reservation> dummyReservations = new ArrayList<>();
        dummyReservations.add(reservation);
        when(reservationRepo.findAll()).thenReturn(dummyReservations);

        //Act
        reservationService.getAll();

        //Assert
        verify(reservationRepo).findAll();
    }

    @Test
    void remove_IdSupplied_ReservationRepoCallsDeleteById() {
        doNothing().when(reservationRepo).deleteById(uuid);

        reservationService.remove(uuid);

        verify(reservationRepo).deleteById(uuid);
    }

    @Test
    void add_DateOutIsAfterDateReturned_ShouldReturnFalse() {
        reservation = new Reservation(uuid, "", null, LocalDate.of(2001, 1, 1), LocalDate.of(2000, 1, 1), null);

        assertThat(reservationService.add(reservation), is(false));
    }

    @Test
    void add_DateOutIsBeforeDateMade_ShouldReturnFalse() {
        reservation = new Reservation(uuid, "", LocalDate.of(2001, 1, 1), LocalDate.of(2000, 1, 1), LocalDate.of(2002, 1, 1), null);

        assertThat(reservationService.add(reservation), is(false));
    }
}




























