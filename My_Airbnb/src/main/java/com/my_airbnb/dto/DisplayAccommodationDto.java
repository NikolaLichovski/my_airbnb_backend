package com.my_airbnb.dto;

import com.my_airbnb.model.domain.Accommodation;
import com.my_airbnb.model.domain.Host;
import com.my_airbnb.model.enumerations.AccommodationCategory;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(Long id, String name, AccommodationCategory category, Long host, Integer numRooms, boolean isRented) {

    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(accommodation.getId(), accommodation.getName(), accommodation.getCategory(), accommodation.getHost().getId(), accommodation.getNumRooms(), accommodation.isRented());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(this.name, this.category, host, this.numRooms, this.isRented);
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }
}
