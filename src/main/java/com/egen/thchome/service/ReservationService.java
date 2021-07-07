package com.egen.thchome.service;

import com.egen.thchome.entity.Reservation;
import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations(int from, int to);
    public Reservation getReservationById(String id);
    public Boolean createReservation(String storeId, Reservation reservation);
    public Boolean cancelReservation(String id);
    public Boolean updateReservation(Reservation reservation);


}
