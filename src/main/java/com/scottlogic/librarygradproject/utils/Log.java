package com.scottlogic.librarygradproject.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scottlogic.librarygradproject.model.Reservation;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Log {
    private boolean isSuccessful;
    private Reservation reservation;
    private String message;
    private HttpStatus httpStatus;

    public Log(){}

    public Log(boolean isSuccessful, Reservation reservation, String message, HttpStatus httpStatus) {
        this.isSuccessful = isSuccessful;
        this.reservation = reservation;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
