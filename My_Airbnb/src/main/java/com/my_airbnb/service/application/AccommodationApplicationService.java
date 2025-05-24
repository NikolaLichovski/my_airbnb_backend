package com.my_airbnb.service.application;

import com.my_airbnb.dto.CreateAccommodationDto;
import com.my_airbnb.dto.DisplayAccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {

    List<DisplayAccommodationDto> findAll();
    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto createAccommodationDto);
    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto);

    void deleteById(Long id);

    Optional<DisplayAccommodationDto> markAsRented(Long id);
    Optional<DisplayAccommodationDto> markAsFree(Long id);
}
