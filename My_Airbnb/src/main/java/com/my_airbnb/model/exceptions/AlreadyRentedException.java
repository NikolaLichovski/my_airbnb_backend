package com.my_airbnb.model.exceptions;

public class AlreadyRentedException extends RuntimeException{
    public AlreadyRentedException(String message) {
        super(message);
    }
}
