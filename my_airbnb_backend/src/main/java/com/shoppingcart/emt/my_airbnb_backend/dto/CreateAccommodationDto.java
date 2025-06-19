package com.shoppingcart.emt.my_airbnb_backend.dto;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Accommodation;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.Host;
import com.shoppingcart.emt.my_airbnb_backend.model.enumerations.AccommodationCategory;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDto(String name, AccommodationCategory category, Long host, Integer numRooms) {
    public static CreateAccommodationDto from(Accommodation accommodation) {
        return new CreateAccommodationDto(accommodation.getName(), accommodation.getCategory(), accommodation.getHost().getId(), accommodation.getNumRooms());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(this.name, this.category, host, this.numRooms);
    }

    public static List<CreateAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccommodationDto::from).collect(Collectors.toList());
    }
}
