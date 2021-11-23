package com.example.demo.exception;

public class EmptyBodyException extends RuntimeException{
    public EmptyBodyException(String message) {
        super(message);
    }

    public EmptyBodyException() {
    }
}
