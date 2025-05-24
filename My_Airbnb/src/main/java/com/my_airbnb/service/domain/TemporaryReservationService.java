package com.my_airbnb.service.domain;

import com.my_airbnb.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationService {
    Optional<TemporaryReservation> addAccommodationToTempList(String username, Long accommodationId);
    void removeAccommodationFromTempList(String username, Long accommodationId);

    List<TemporaryReservation> getUserTempList(String username);
    Optional<List<TemporaryReservation>> confirmAllReservations(String username);

}
