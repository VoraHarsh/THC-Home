package com.egen.thchome.service.impl;

import com.egen.thchome.entity.Reservation;
import com.egen.thchome.entity.Store;
import com.egen.thchome.enums.ReservationStatus;
import com.egen.thchome.exception.ReservationServiceException;
import com.egen.thchome.exception.StoreServiceException;
import com.egen.thchome.repository.ReservationRepository;
import com.egen.thchome.repository.StoreRepository;
import com.egen.thchome.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;
    StoreRepository storeRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, StoreRepository storeRepository){
        this.reservationRepository = reservationRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        try {
            List<Reservation> reservationList = reservationRepository.findAll();
            return reservationList;
        }
        catch (Exception e){
            log.error("Error occurred in getting all the Reservations: " + e.getMessage());
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public Reservation getReservationById(String id) {
        try {
            Reservation reservation = reservationRepository.findByReservationId(id);
            return reservation;
        }
        catch (Exception e){
            log.error("Error occurred in getting the Reservation: " + e.getMessage());
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean createReservation(String storeId, Reservation reservation) {
        try{
            reservation.setReservationCreated(new Timestamp(System.currentTimeMillis()));
            Store store = storeRepository.findByStoreId(storeId);

            if(store == null){
                throw new StoreServiceException("Store does not exist");
            }

            Set<Reservation> reservationSet = store.getReservation();

            for (Reservation reservation1 : reservationSet){
                if(reservation1.getStartTime().compareTo(reservation.getStartTime()) == 0){
                    throw new ReservationServiceException("Reservation with same Time Exists");
                }
            }

            Set<Reservation> existingReservations = store.getReservation();
            existingReservations.add(reservation);
            store.setReservation(existingReservations);
            storeRepository.save(store);
            return true;
        }
        catch(Exception e){
            log.error("Error occurred in creating Reservation: " + e.getMessage());
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean cancelReservation(String id) {
        try{
            Reservation existingReservation = reservationRepository.findByReservationId(id);
            if(existingReservation == null){
                throw new ReservationServiceException("Reservation not found");
            }
            else{
                existingReservation.setReservationStatus(ReservationStatus.CANCELLED);
                reservationRepository.save(existingReservation);
                return true;
            }
        }
        catch(Exception e){
            log.error("Error occurred in cancelling the Reservation: " + e.getMessage());
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean updateReservation(Reservation reservation) {
        try{
            Reservation existingReservation = reservationRepository.findByReservationId(reservation.getReservationId());
            if(existingReservation == null){
                throw new ReservationServiceException("Reservation does not exist");
            }
            else{
                reservation.setReservationStatus(ReservationStatus.COMPLETED);
                reservationRepository.save(reservation);
                return true;
            }
        }
        catch(Exception e){
            log.error("Error occurred in updating the Reservation: " + e.getMessage());
            throw new ReservationServiceException(e.getMessage());
        }
    }
}
