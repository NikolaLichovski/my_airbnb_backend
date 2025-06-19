package com.shoppingcart.emt.my_airbnb_backend.model.exceptions;

public class AlreadyInTemporaryReservationList extends RuntimeException{
    public AlreadyInTemporaryReservationList(String message) {
        super(message);
    }
}
