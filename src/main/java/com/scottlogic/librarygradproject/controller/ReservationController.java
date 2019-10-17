package com.scottlogic.librarygradproject.controller;

import com.scottlogic.librarygradproject.model.Reservation;
import com.scottlogic.librarygradproject.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(
            ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Reservation find(@PathVariable(value = "id") UUID id) throws NoSuchElementException {
        return reservationService.find(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") UUID id) {
        reservationService.remove(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post(@RequestBody() Reservation reservation) {
        reservationService.add(reservation);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public boolean put(@RequestBody() Reservation reservation) {
        return reservationService.put(reservation);
    }
}
