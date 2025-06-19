package com.shoppingcart.emt.my_airbnb_backend.model.exceptions;

public class AlreadyRentedException extends RuntimeException{
    public AlreadyRentedException(String message) {
        super(message);
    }
}
