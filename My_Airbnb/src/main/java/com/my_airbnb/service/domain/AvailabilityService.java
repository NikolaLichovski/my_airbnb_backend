package com.my_airbnb.service.domain;

import com.my_airbnb.model.domain.Availability;

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
