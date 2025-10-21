package com.takehome.stayease.exceptions;

public class RoomsNotAvailableException extends RuntimeException {
    
    public RoomsNotAvailableException(String message) {
        super(message);
    }
    
}