package com.shoppingcart.emt.my_airbnb_backend.service.domain;

import com.shoppingcart.emt.my_airbnb_backend.dto.AccommodationDetailsDto;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.Accommodation;
import com.shoppingcart.emt.my_airbnb_backend.model.views.AccommodationsPerHostView;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> save(Accommodation accommodation);
    Optional<Accommodation> update(Long id, Accommodation accommodation);

    void deleteById(Long id);

    Optional<Accommodation> markAsRented(Long id);
    Optional<Accommodation> markAsFree(Long id);

    List<AccommodationsPerHostView> getAccommodationsPerHost();
    void refreshMaterializedView();

    Optional<AccommodationDetailsDto> getAccommodationDetails(Long id);


}
