package com.shoppingcart.emt.my_airbnb_backend.dto;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Accommodation;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.TemporaryReservation;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public record CreateTemporaryReservationDto (String username, Long accommodationId) {
    public static CreateTemporaryReservationDto from(TemporaryReservation reservation) {
        return new CreateTemporaryReservationDto(reservation.getUser().getUsername(), reservation.getAccommodation().getId());
    }

    public TemporaryReservation toTemporaryReservation(User user, Accommodation accommodation) {
        return new TemporaryReservation(user, accommodation);
    }

    public static List<CreateTemporaryReservationDto> from(List<TemporaryReservation> reservations) {
        return reservations.stream().map(CreateTemporaryReservationDto::from).collect(Collectors.toList());
    }
}
