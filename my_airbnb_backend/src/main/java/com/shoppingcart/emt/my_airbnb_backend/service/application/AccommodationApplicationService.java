package com.shoppingcart.emt.my_airbnb_backend.service.application;

import com.shoppingcart.emt.my_airbnb_backend.dto.*;
import com.shoppingcart.emt.my_airbnb_backend.model.views.AccommodationsPerHostView;

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

    List<AccommodationsPerHostView> getAccommodationsPerHost();

    Optional<AccommodationDetailsDto> getAccommodationDetails(Long id);


}
