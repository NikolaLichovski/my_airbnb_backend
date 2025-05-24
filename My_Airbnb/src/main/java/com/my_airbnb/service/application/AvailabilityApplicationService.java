package com.my_airbnb.service.application;

import com.my_airbnb.dto.CreateAvailabilityDto;
import com.my_airbnb.dto.DisplayAvailabilityDto;

import java.util.List;
import java.util.Optional;

public interface AvailabilityApplicationService {
    List<DisplayAvailabilityDto> findAll();
    Optional<DisplayAvailabilityDto> findById(Long id);

    Optional<DisplayAvailabilityDto> save(CreateAvailabilityDto createAvailabilityDto);
    Optional<DisplayAvailabilityDto> update(Long id, CreateAvailabilityDto createAvailabilityDto);

    void deleteById(Long id);

    List<DisplayAvailabilityDto> findByAccommodationId(Long accommodationId);

}
