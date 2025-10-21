package com.takehome.stayease.exceptions;

public class InvalidGuestCountException extends RuntimeException {

    public InvalidGuestCountException(String message) {
        super(message);
    }
    
}