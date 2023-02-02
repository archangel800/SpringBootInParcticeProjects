package com.dzidziguri.loggingapplication.loggingspringapplication.exception;

import com.dzidziguri.loggingapplication.loggingspringapplication.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception exception){
        return new ResponseEntity<>("Exception has occurred due to the client", HttpStatus.BAD_REQUEST);
    }
}
