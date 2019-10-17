package com.scottlogic.librarygradproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.scottlogic.librarygradproject.model.Reservation;
import com.scottlogic.librarygradproject.repository.ReservationRepo;
import com.scottlogic.librarygradproject.service.ReservationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class ReservationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private ReservationService reservationService;

    private UUID uuid;
    private Reservation reservation;
    private ObjectMapper objectMapper = new ObjectMapper();
    private String json;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        objectMapper.registerModule(new JavaTimeModule());
        reservation = new Reservation(
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

    @AfterEach
    void tearDown() {
    }

    @Test
    void post_CorrectParameters_ShouldReturn200Status() throws Exception {
        mockMvc.perform(
                post("/reservations")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andReturn();
    }
    //Get check that correct reservation returned
    @Test
    void get_SuppliedOneReservationInDb_ShouldReturn200Status() throws Exception {
        reservationService.add(reservation);

        MvcResult result = mockMvc.perform(
                get("/reservations/{id}", uuid)
        )
                .andReturn();

        Reservation returnRes = objectMapper.readValue(result.getResponse().getContentAsString(), Reservation.class);
        assertThat( reservation, is(returnRes));
    }

    @Test
    void get_SuppliedOneReservationInDb_ShouldReturnCorrectReservation() throws Exception {
        mockMvc.perform(
                get("/reservations")
        )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAll_NoParameters_ShouldReturn200Status() throws Exception {
        mockMvc.perform(
                get("/reservations")
        )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void put_CorrectParameters_ShouldReturn200Status() throws Exception {
        mockMvc.perform(
                put("/reservations")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void delete_MatchingIdShouldReturn200Status() throws Exception {
        reservationService.add(reservation);
        mockMvc.perform(
                delete("/reservations/{id}", uuid)
        )
                .andExpect(status().isOk())
                .andReturn();
    }
}