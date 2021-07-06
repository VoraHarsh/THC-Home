package com.egen.thchome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ReservationServiceException extends RuntimeException{
    public ReservationServiceException(String message){
        super(message);
    }
}
