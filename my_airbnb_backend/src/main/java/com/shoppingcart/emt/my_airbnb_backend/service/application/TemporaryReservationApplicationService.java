package com.shoppingcart.emt.my_airbnb_backend.service.application;

import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayTemporaryReservationDto;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationApplicationService {
    Optional<DisplayTemporaryReservationDto> addAccommodationToTempList(String username, Long accommodationId);
    void removeAccommodationFromTempList(String username, Long accommodationId);

    List<DisplayTemporaryReservationDto> getUserTempList(String username);
    Optional<List<DisplayTemporaryReservationDto>> confirmAllReservations(String username);
}
