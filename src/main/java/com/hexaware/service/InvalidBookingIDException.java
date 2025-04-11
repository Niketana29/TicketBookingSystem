package com.hexaware.service;

public class InvalidBookingIDException extends Exception {
    public InvalidBookingIDException(String message) {
        super(message);
    }
}

