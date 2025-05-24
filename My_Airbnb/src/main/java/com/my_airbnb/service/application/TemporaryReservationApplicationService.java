package com.my_airbnb.service.application;

import com.my_airbnb.dto.DisplayTemporaryReservationDto;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationApplicationService {
    Optional<DisplayTemporaryReservationDto> addAccommodationToTempList(String username, Long accommodationId);
    void removeAccommodationFromTempList(String username, Long accommodationId);

    List<DisplayTemporaryReservationDto> getUserTempList(String username);
    Optional<List<DisplayTemporaryReservationDto>> confirmAllReservations(String username);
}
