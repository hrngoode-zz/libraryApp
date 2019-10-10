package com.scottlogic.librarygradproject.controller;

import com.scottlogic.librarygradproject.model.Reservation;
import com.scottlogic.librarygradproject.service.ReservationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(
            @Qualifier("DummyData") ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Reservation get(@PathVariable(value = "id") UUID id) {
        return reservationService.get(id);
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
