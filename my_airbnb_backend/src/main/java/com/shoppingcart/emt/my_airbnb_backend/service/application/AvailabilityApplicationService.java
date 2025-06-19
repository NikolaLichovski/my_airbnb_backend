package com.shoppingcart.emt.my_airbnb_backend.service.application;

import com.shoppingcart.emt.my_airbnb_backend.dto.CreateAvailabilityDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayAvailabilityDto;

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
