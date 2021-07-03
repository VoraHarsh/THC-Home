package com.egen.thchome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class StoreServiceException extends RuntimeException{
    public StoreServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
