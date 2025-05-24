package com.my_airbnb.service.domain;

import com.my_airbnb.model.domain.Accommodation;

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
}
