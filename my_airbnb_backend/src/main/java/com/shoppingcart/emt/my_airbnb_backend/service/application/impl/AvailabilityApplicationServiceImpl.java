package com.shoppingcart.emt.my_airbnb_backend.service.application.impl;

import com.shoppingcart.emt.my_airbnb_backend.dto.CreateAvailabilityDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayAvailabilityDto;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.Accommodation;
import com.shoppingcart.emt.my_airbnb_backend.service.application.AvailabilityApplicationService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.AccommodationService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.AvailabilityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailabilityApplicationServiceImpl implements AvailabilityApplicationService {

    private final AvailabilityService availabilityService;
    private final AccommodationService accommodationService;

    public AvailabilityApplicationServiceImpl(AvailabilityService availabilityService, AccommodationService accommodationService) {
        this.availabilityService = availabilityService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<DisplayAvailabilityDto> findAll() {
        return DisplayAvailabilityDto.from(availabilityService.findAll());
    }

    @Override
    public Optional<DisplayAvailabilityDto> findById(Long id) {
        return availabilityService.findById(id).map(DisplayAvailabilityDto::from);
    }

    @Override
    public Optional<DisplayAvailabilityDto> save(CreateAvailabilityDto createAvailabilityDto) {
        Optional<Accommodation> accommodation = accommodationService.findById(createAvailabilityDto.accommodation());
        if (accommodation.isPresent()) {
            return availabilityService.save(createAvailabilityDto.toAvailability(accommodation.get())).map(DisplayAvailabilityDto::from);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisplayAvailabilityDto> update(Long id, CreateAvailabilityDto createAvailabilityDto) {
        Optional<Accommodation> accommodation = accommodationService.findById(createAvailabilityDto.accommodation());
        return availabilityService.update(id, createAvailabilityDto.toAvailability(accommodation.orElse(null))).map(DisplayAvailabilityDto::from);
    }

    @Override
    public void deleteById(Long id) {
        availabilityService.deleteById(id);
    }

    @Override
    public List<DisplayAvailabilityDto> findByAccommodationId(Long accommodationId) {
        return availabilityService.findByAccommodationId(accommodationId).stream().map(DisplayAvailabilityDto::from).collect(Collectors.toList());
    }
}
