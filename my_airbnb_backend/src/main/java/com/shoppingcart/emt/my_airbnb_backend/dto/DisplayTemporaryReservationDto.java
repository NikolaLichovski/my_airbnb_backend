package com.shoppingcart.emt.my_airbnb_backend.dto;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Accommodation;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.TemporaryReservation;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayTemporaryReservationDto(Long id, String username, Long accommodationId) {
    public static DisplayTemporaryReservationDto from(TemporaryReservation reservation) {
        return new DisplayTemporaryReservationDto(reservation.getId(), reservation.getUser().getUsername(), reservation.getAccommodation().getId());
    }

    public TemporaryReservation toTemporaryReservation(User user, Accommodation accommodation) {
        return new TemporaryReservation(user, accommodation);
    }

    public static List<DisplayTemporaryReservationDto> from (List<TemporaryReservation> reservations) {
        return reservations.stream().map(DisplayTemporaryReservationDto::from).collect(Collectors.toList());
    }
}
