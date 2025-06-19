package com.shoppingcart.emt.my_airbnb_backend.service.application.impl;

import com.shoppingcart.emt.my_airbnb_backend.dto.AccommodationDetailsDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.CreateAccommodationDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayAccommodationDto;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.Host;
import com.shoppingcart.emt.my_airbnb_backend.model.views.AccommodationsPerHostView;
import com.shoppingcart.emt.my_airbnb_backend.service.application.AccommodationApplicationService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.AccommodationService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }


    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto createAccommodationDto) {
        Optional<Host> host = hostService.findById(createAccommodationDto.host());

        if (host.isPresent()) {
            return accommodationService.save(createAccommodationDto.toAccommodation(host.get())).map(DisplayAccommodationDto::from);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto) {
        Optional<Host> host = hostService.findById(createAccommodationDto.host());

        return accommodationService.update(id, createAccommodationDto.toAccommodation(host.orElse(null))).map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationService.markAsRented(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsFree(Long id) {
        return accommodationService.markAsFree(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<AccommodationsPerHostView> getAccommodationsPerHost() {
        return accommodationService.getAccommodationsPerHost();
    }

    @Override
    public Optional<AccommodationDetailsDto> getAccommodationDetails(Long id) {
        return accommodationService.getAccommodationDetails(id);
    }


}
