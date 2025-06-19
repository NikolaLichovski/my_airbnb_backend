package com.shoppingcart.emt.my_airbnb_backend.dto;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Accommodation;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.Availability;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayAvailabilityDto(Long id,LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, Double price, Long accommodation) {
    public static DisplayAvailabilityDto from(Availability availability) {
        return new DisplayAvailabilityDto(availability.getId(), availability.getDateTimeStart(), availability.getDateTimeEnd(), availability.getPrice(), availability.getAccommodation().getId());
    }

    public static List<DisplayAvailabilityDto> from(List<Availability> availabilities) {
        return availabilities.stream().map(DisplayAvailabilityDto::from).collect(Collectors.toList());
    }

    public Availability toAvailability(Accommodation accommodation) {
        return new Availability(dateTimeStart, dateTimeEnd, price, accommodation);
    }
}
