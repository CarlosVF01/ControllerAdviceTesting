package com.example.demo.exception;

import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.exception.EmptyBodyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EmptyBodyException.class)
    public ResponseEntity<String> handleEmptyBody(EmptyBodyException emptyBodyException){
        log.error("New EmptyBodyException, reason: {}", emptyBodyException.getMessage());
        return new ResponseEntity<>("Body input is empty", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<String> handleElementNotFound(ElementNotFoundException elementNotFoundException){
        log.error("A ElementNotFoundException ocurred, reason: {}", elementNotFoundException.getMessage());
        return new ResponseEntity<>("No element could be found with the given information", HttpStatus.NOT_FOUND);
    }
}
