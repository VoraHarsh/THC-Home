package com.egen.thchome.controllers;

import com.egen.thchome.entity.Reservation;
import com.egen.thchome.response.Response;
import com.egen.thchome.response.ResponseMetadata;
import com.egen.thchome.response.StatusMessage;
import com.egen.thchome.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {

    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/{from}/{to}", produces = "application/json")
    @ApiOperation(value  = "Returns a List of all Reservations")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<List<Reservation>> getAllReservations(@PathVariable("from") int from,@PathVariable("to") int to){
        return Response.<List<Reservation>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(reservationService.getAllReservations(from,to))
                .build();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value  = "Returns a single Reservation given the ReservationId")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<Reservation> getReservationById(@PathVariable("id") String id){
        return Response.<Reservation>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(reservationService.getReservationById(id))
                .build();
    }

    @PostMapping( value = "/{storeId}/createReservation", consumes = "application/json", produces = "application/json")
    @ApiOperation(value  = "Creates Reservation")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<String> createReservation(@PathVariable("storeId") String storeId, @RequestBody Reservation reservation){
        return reservationService.createReservation(storeId, reservation) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Reservation Created")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Reservation Not Created")
                        .build();
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    @ApiOperation(value  = "Update Reservation")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<String> updateReservation(@RequestBody Reservation reservation){
        return reservationService.updateReservation(reservation) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Reservation Updated")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Reservation Not Updated")
                        .build();
    }


    @PostMapping(value = "/cancel/{id}")
    @ApiOperation(value  = "Cancel Reservation")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<String> cancelReservation(@PathVariable("id") String id){
        return reservationService.cancelReservation(id) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Reservation Deleted")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Reservation Could not be Deleted")
                        .build();
    }



}
