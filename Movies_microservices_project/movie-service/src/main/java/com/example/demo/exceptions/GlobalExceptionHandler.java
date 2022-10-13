package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFound.class})
    public ResponseEntity enCasoDeExcepcionNotFound(NotFound exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({ExceedsLimitException.class})
    public ResponseEntity enCasoDeExcepcionExceedsLimit(ExceedsLimitException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity enCasoDeExcepcionAlreadyExists(AlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler({NoResultsException.class})
    public ResponseEntity enCasoDeExcepcionNoResultsException(NoResultsException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
