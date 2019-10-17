package com.scottlogic.librarygradproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.scottlogic.librarygradproject.model.Reservation;
import com.scottlogic.librarygradproject.repository.ReservationRepo;
import com.scottlogic.librarygradproject.service.ReservationService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ReservationExceptionIT {


    //TODO: when you make your exception handler remember to change these to more specific exceptions
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private ReservationService reservationService;


    private UUID uuid;
    private ObjectMapper objectMapper = new ObjectMapper();
    private String json;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @BeforeEach
    void setUp(){
        objectMapper.registerModule(new JavaTimeModule());
        Reservation reservation = new Reservation(
                uuid,
                "post name",
                LocalDate.parse("2019-11-11"),
                LocalDate.parse("2040-11-11"),
                LocalDate.parse("2043-11-11"),
                UUID.randomUUID()
        );
        try {
            json = objectMapper.writeValueAsString(reservation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get_AttemptToGetReservationThatDoesNotExist_ShouldThrowNoSuchElementException() {
        uuid = UUID.randomUUID();
        assertThrows(NestedServletException.class, () -> mockMvc.perform(
                get("/reservations/{id}", uuid)
        )
                .andReturn());
    }

    @Test
    public void get_AttemptToDeleteReservationThatDoesNotExist_ShouldThrowNoSuchElementException() {
        //TODO: Delete non-existence needs to throw BadRequest or NoSuchElementException
    }

    @Test
    public void get_AttemptToPutReservationThatDoesNotExist_ShouldThrowNoSuchElementException() {
        //TODO: Fill once you know what exception you want to throw
    }

    @Test
    public void get_AttemptToAddInvalidReservation_ShouldThrowNoSuchElementException() {
        //TODO: Add throws BadRequest if submitted with an invalid Reservation
    }

}
