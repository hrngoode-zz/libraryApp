package com.scottlogic.librarygradproject.service;

import com.scottlogic.librarygradproject.model.Reservation;
import com.scottlogic.librarygradproject.repository.ReservationRepo;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReservationService implements ServiceInterface<Reservation> {

    private ReservationRepo reservationRepo;

    public ReservationService(ReservationRepo reservationRepo){
        this.reservationRepo = reservationRepo;
    }

    public ReservationService(ReservationRepo reservationRepo, List<Reservation> reservations){
        this.reservationRepo = reservationRepo;
        reservations.forEach(reservationRepo::save);
    }

    @Override
    public Reservation find(UUID id) {
        return reservationRepo
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    public void remove(UUID id) {
        reservationRepo.deleteById(id);
    }

    public boolean add(@NotNull Reservation reservation) {
        //check if dates make sense
        if(!isReservationValid(reservation)) return false;

        //check if book is already reserved at this time
        List<Reservation> allBookRes = reservationRepo
                .findByBookId(reservation.getBookId())
                .stream()
                .filter(
                        requestedRes -> !(
                                (reservation.getDateOut().isAfter(requestedRes.getDateReturned()))
                                || (reservation.getDateReturned().isBefore(requestedRes.getDateOut()))
                        )
                )
                .collect(Collectors.toList());

        if(allBookRes.size() == 0) {
            reservationRepo.save(reservation);
            return true;
        }

        return false;
    }

    public boolean put(Reservation reservation) {
        reservationRepo
                .findById(reservation.getId())
                .ifPresent(reservationRepo::delete);

        return add(reservation);
    }

    private boolean isReservationValid(Reservation reservation) {
        return !(
                reservation.getDateOut().isAfter(reservation.getDateReturned())
                || reservation.getDateOut().isBefore(reservation.getDateMade())
                );
    }
}
