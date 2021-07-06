package com.egen.thchome.repository;

import com.egen.thchome.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    Reservation findByReservationId(String reservationId);
}
