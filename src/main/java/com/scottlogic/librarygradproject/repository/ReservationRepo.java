package com.scottlogic.librarygradproject.repository;

import com.scottlogic.librarygradproject.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationRepo extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByBookId(UUID bookId);
}

