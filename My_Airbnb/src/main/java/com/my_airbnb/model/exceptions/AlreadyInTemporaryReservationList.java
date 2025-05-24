package com.my_airbnb.model.exceptions;

public class AlreadyInTemporaryReservationList extends RuntimeException{
    public AlreadyInTemporaryReservationList(String message) {
        super(message);
    }
}
