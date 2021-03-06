package com.egen.thchome.exception.handler;

import com.egen.thchome.exception.MenuServiceException;
import com.egen.thchome.exception.ReservationServiceException;
import com.egen.thchome.exception.StoreServiceException;
import com.egen.thchome.response.Response;
import com.egen.thchome.response.ResponseMetadata;
import com.egen.thchome.response.StatusMessage;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(StoreServiceException.class)
    public ResponseEntity<Response<?>> handleStoreServiceException(StoreServiceException e){
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(MenuServiceException.class)
    public ResponseEntity<Response<?>> handleMenuServiceException(MenuServiceException e){
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(ReservationServiceException.class)
    public ResponseEntity<Response<?>> handleReservationServiceException(ReservationServiceException e){
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<Response<?>> buildResponse(StatusMessage statusMessage, HttpStatus status, String errors){
        var response = Response.builder()
                .meta(ResponseMetadata.builder()
                        .statusMessage(statusMessage.name())
                        .statusCode(status.value())
                        .build())
                .data(errors)
                .build();
        return ResponseEntity.status(status)
                .body(response);
    }
}
