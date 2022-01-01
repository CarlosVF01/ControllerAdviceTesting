package com.example.demo.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(Class className, String id) {
        super(className.getSimpleName() + " with the ID: " + id + " was not found");
    }

    public ElementNotFoundException(String message) {
        super(message);
    }

}
