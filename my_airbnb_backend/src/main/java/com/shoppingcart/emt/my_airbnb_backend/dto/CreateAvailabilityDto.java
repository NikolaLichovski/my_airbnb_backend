package com.shoppingcart.emt.my_airbnb_backend.dto;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Accommodation;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.Availability;

import java.time.LocalDateTime;
import java.util.List;

public record CreateAvailabilityDto(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, Double price, Long accommodation) {
    public static CreateAvailabilityDto from(Availability availability) {
        return new CreateAvailabilityDto(availability.getDateTimeStart(), availability.getDateTimeEnd(), availability.getPrice(), availability.getAccommodation().getId());
    }
    public static List<CreateAvailabilityDto> from(List<Availability> availabilities) {
        return availabilities.stream().map(CreateAvailabilityDto::from).toList();
    }

    public Availability toAvailability(Accommodation accommodation) {
        return new Availability(dateTimeStart, dateTimeEnd, price, accommodation);
    }
}
