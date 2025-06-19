package com.shoppingcart.emt.my_airbnb_backend.service.domain;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Availability;

import java.util.List;
import java.util.Optional;

public interface AvailabilityService {

    List<Availability> findAll();
    Optional<Availability> findById(Long id);

    Optional<Availability> save(Availability availability);
    Optional<Availability> update(Long id, Availability availability);

    void deleteById(Long id);

    List<Availability> findByAccommodationId(Long accommodationId);
}
