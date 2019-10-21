package com.scottlogic.librarygradproject.service;

import com.scottlogic.librarygradproject.model.Reservation;
import com.scottlogic.librarygradproject.repository.ReservationRepo;
import com.scottlogic.librarygradproject.utils.Log;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReservationService implements ServiceInterface<Log> {

    private ReservationRepo reservationRepo;

    public ReservationService(ReservationRepo reservationRepo){
        this.reservationRepo = reservationRepo;
    }

    public ReservationService(ReservationRepo reservationRepo, List<Reservation> reservations){
        this.reservationRepo = reservationRepo;
        reservations.forEach(reservationRepo::save);
    }

    @Override
    public Log find(UUID id) {
        try {
            Reservation toBeReturned = reservationRepo
                    .findById(id)
                    .orElseThrow(NoSuchElementException::new);
            return foundReservationSuccessfully(toBeReturned);
        } catch (NoSuchElementException nse) {
            return noReservationOfThatIdFound(id);
        }
    }

    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    public Log remove(UUID id) {
        try {
            reservationRepo.deleteById(id);
            return deleteReservationSuccessful(id);
        } catch (IllegalArgumentException ia) {
            return noReservationOfThatIdFound(id);
        }
    }

    public Log add(@NotNull Reservation reservation) {

        //check if dates make sense
        if(!isReservationValid(reservation)) return badDatesRequested(reservation);

        //check if book is already reserved at this time
        List<Reservation> allBookRes = reservationRepo
                .findByBookId(reservation.getBookId())
                .stream()
                .filter(
                        requestedRes -> (
                                !(reservation.getDateOut().isAfter(requestedRes.getDateReturned()))
                                && !(reservation.getDateReturned().isBefore(requestedRes.getDateOut()))
                        )
                )
                .collect(Collectors.toList());

        if(allBookRes.size() == 0) {
            reservationRepo.save(reservation);
            return successfulReservationInput(reservation);
        }
        else return unsuccessfulReservationInput(reservation);
    }

    public Log put(Reservation reservation) {
        if(find(reservation.getId()).isSuccessful()) remove(reservation.getId());
        return add(reservation);
    }

    private boolean isReservationValid(Reservation reservation) {
        return (
                ! reservation.getDateOut().isAfter(reservation.getDateReturned())
                && ! reservation.getDateOut().isBefore(reservation.getDateMade())
                );
    }

    private Log noReservationOfThatIdFound(UUID uuid){
        return new Log(
                false,
                null,
                "No reservation of id: " + uuid.toString() + " found in database.",
                HttpStatus.BAD_REQUEST
        );
    }

    private Log foundReservationSuccessfully(Reservation reservation){
        return new Log(
                true,
                reservation,
                "Successfully retrieved reservation with id: " + reservation.getId().toString(),
                HttpStatus.OK
        );
    }

    private Log badDatesRequested(Reservation reservation){
        return new Log(
                false,
                reservation,
                "Invalid dates, try again!",
                HttpStatus.BAD_REQUEST
        );
    }

    private Log successfulReservationInput(Reservation reservation){
        return new Log(
                true,
                reservation,
                "Successfully made this reservation.",
                HttpStatus.OK
        );
    }

    private Log unsuccessfulReservationInput(Reservation reservation){
        return new Log(
                false,
                reservation,
                "Unsuccessfully made this reservation due to conflicting with pre-existing reservation",
                HttpStatus.FORBIDDEN
        );
    }

    private Log deleteReservationSuccessful(UUID uuid) {
        return new Log(
                true,
                null,
                "Successfully deleted reservation with id: " + uuid.toString(),
                HttpStatus.OK
        );
    }
}
