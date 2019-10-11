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
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ReservationServiceTest {

    @Mock
    ReservationRepo reservationRepo;

    @MockBean
    ReservationService reservationService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void Check_Get_Method_Calls_Repo_FindById() {
        //Arrange

        //Act

        //Assert

    }

    @Test
    void getAll() {
    }

    @Test
    void remove() {
        reservationService.add(new Reservation());

    }

    @Test
    void add() {
    }

    @Test
    void put() {
    }
}