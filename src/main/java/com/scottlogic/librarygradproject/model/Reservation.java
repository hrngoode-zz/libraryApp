package com.scottlogic.librarygradproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue
    private UUID id;

    private String partyName;
    private LocalDate dateOut;
    private LocalDate dateReturned;
    private LocalDate dateMade;
    private UUID bookId;

    public Reservation() {

    }


    public Reservation(UUID id, String partyName, LocalDate dateMade, LocalDate dateOut, LocalDate dateReturned, UUID bookId) {
        this.id = id;
        this.partyName = partyName;
        this.dateMade = dateMade;
        this.dateOut = dateOut;
        this.dateReturned = dateReturned;
        this.bookId = bookId;
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
    public int hashCode() {
        return 31 * id.hashCode();
    }

    public UUID getBookId() {
        return bookId;
    }
}
