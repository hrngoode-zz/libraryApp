package com.scottlogic.librarygradproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    private UUID id;

    private String partyName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOut;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReturned;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateMade;
    private UUID bookId;

    public Reservation() {
    }

    public Reservation(String partyName, LocalDate dateMade, LocalDate dateOut, LocalDate dateReturned, UUID bookId) {
        this.partyName = partyName;
        this.dateMade = dateMade;
        this.dateOut = dateOut;
        this.dateReturned = dateReturned;
        this.bookId = bookId;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public LocalDate getDateMade() {
        return dateMade;
    }

    public void setDateMade(LocalDate dateMade) {
        this.dateMade = dateMade;
    }


    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation reservation = (Reservation) o;

        if (!id.equals(reservation.id)) return false;
        if (partyName != null ? !partyName.equals(reservation.partyName) : reservation.partyName != null) return false;
        if (dateOut != null ? !dateOut.equals(reservation.dateOut) : reservation.dateOut != null) return false;
        if (dateReturned != null ? !dateReturned.equals(reservation.dateReturned) : reservation.dateReturned != null) return false;
        if (dateMade != null ? !dateMade.equals(reservation.dateMade) : reservation.dateMade != null) return false;

        return bookId != null ? bookId.equals(reservation.bookId) : reservation.bookId == null;
    }

    @Override
    public int hashCode() {
        return 31 * id.hashCode();
    }

    public UUID getBookId() {
        return bookId;
    }
}
