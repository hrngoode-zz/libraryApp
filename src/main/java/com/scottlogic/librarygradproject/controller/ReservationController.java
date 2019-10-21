package com.scottlogic.librarygradproject.controller;

import com.scottlogic.librarygradproject.model.Reservation;
import com.scottlogic.librarygradproject.service.ReservationService;
import com.scottlogic.librarygradproject.utils.Log;
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
    public Log find(@PathVariable(value = "id") UUID id) throws NoSuchElementException {
        return reservationService.find(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Log delete(@PathVariable(value = "id") UUID id) {
        return reservationService.remove(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Log post(@RequestBody() Reservation reservation) {
        return reservationService.add(reservation);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Log put(@RequestBody() Reservation reservation) {
        return reservationService.put(reservation);
    }
}
