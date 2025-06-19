package com.shoppingcart.emt.my_airbnb_backend.service.application.impl;

import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayTemporaryReservationDto;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.TemporaryReservation;
import com.shoppingcart.emt.my_airbnb_backend.service.application.TemporaryReservationApplicationService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.TemporaryReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationApplicationServiceImpl implements TemporaryReservationApplicationService {

    private final TemporaryReservationService temporaryReservationService;

    public TemporaryReservationApplicationServiceImpl(TemporaryReservationService temporaryReservationService) {
        this.temporaryReservationService = temporaryReservationService;
    }

    @Override
    public Optional<DisplayTemporaryReservationDto> addAccommodationToTempList(String username, Long accommodationId) {
        Optional<TemporaryReservation> temporaryReservation = temporaryReservationService.addAccommodationToTempList(username, accommodationId);
        if (temporaryReservation.isPresent()) {
            return Optional.of(DisplayTemporaryReservationDto.from(temporaryReservation.get()));
        }
        return Optional.empty();
    }

    @Override
    public void removeAccommodationFromTempList(String username, Long accommodationId) {
        temporaryReservationService.removeAccommodationFromTempList(username, accommodationId);
    }

    @Override
    public List<DisplayTemporaryReservationDto> getUserTempList(String username) {
        return DisplayTemporaryReservationDto.from(temporaryReservationService.getUserTempList(username));
    }

    @Override
    public Optional<List<DisplayTemporaryReservationDto>> confirmAllReservations(String username) {
        Optional<List<TemporaryReservation>> list = temporaryReservationService.confirmAllReservations(username);
        if (list.isPresent()) {
            return Optional.of(DisplayTemporaryReservationDto.from(list.get()));
        }
        return Optional.empty();
    }
}
