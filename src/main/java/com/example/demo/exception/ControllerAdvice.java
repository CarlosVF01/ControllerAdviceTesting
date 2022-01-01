package com.example.demo.exception;


import com.example.demo.aws.s3test.S3Test;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    private static final S3Test s3Test = new S3Test();

    @ExceptionHandler(EmptyBodyException.class)
    public ResponseEntity<String> handleEmptyBody(EmptyBodyException exception){
        log.error("New EmptyBodyException, reason: {}", exception.getMessage());
        s3Test.uploadErrorLog(exception.getClass().getSimpleName(), exception.getMessage());
        return new ResponseEntity<>("Body input is empty", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<String> handleElementNotFound(ElementNotFoundException exception){
        log.error("A ElementNotFoundException ocurred, reason: {}", exception.getMessage());
        s3Test.uploadErrorLog(exception.getClass().getSimpleName(), exception.getMessage());
        return new ResponseEntity<>("No element could be found with the given information", HttpStatus.NOT_FOUND);
    }
}
