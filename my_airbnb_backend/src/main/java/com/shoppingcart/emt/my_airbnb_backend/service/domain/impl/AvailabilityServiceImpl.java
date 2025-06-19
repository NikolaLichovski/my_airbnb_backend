package com.shoppingcart.emt.my_airbnb_backend.service.domain.impl;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Availability;
import com.shoppingcart.emt.my_airbnb_backend.repository.AvailabilityRepository;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.AccommodationService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.AvailabilityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final AccommodationService accommodationService;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository, AccommodationService accommodationService) {
        this.availabilityRepository = availabilityRepository;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    @Override
    public Optional<Availability> findById(Long id) {
        return availabilityRepository.findById(id);
    }

    @Override
    public Optional<Availability> save(Availability availability) {
        return Optional.of(availabilityRepository.save(availability));
    }

    @Override
    public Optional<Availability> update(Long id, Availability availability) {
        return availabilityRepository.findById(id).map(existingAvailability -> {
            if (availability.getDateTimeStart() != null) {
                existingAvailability.setDateTimeStart(availability.getDateTimeStart());
            }
            if (availability.getDateTimeEnd() != null) {
                existingAvailability.setDateTimeEnd(availability.getDateTimeEnd());
            }
            if (availability.getPrice() != null) {
                existingAvailability.setPrice(availability.getPrice());
            }
            if (availability.getAccommodation() != null && accommodationService.findById(availability.getAccommodation().getId()).isPresent()) {
                existingAvailability.setAccommodation(accommodationService.findById(availability.getAccommodation().getId()).get());
            }
            return availabilityRepository.save(existingAvailability);
        });
    }

    @Override
    public void deleteById(Long id) {
        availabilityRepository.deleteById(id);
    }

    @Override
    public List<Availability> findByAccommodationId(Long accommodationId) {
        return availabilityRepository.findByAccommodationId(accommodationId);
    }

    /*public List<Availability> getAvailabilitiesByAccommodationId(Long accommodationId) {
        return availabilityRepository.findAll().stream()
                .filter(a -> a.getAccommodation().getId().equals(accommodationId))
                .toList();
    }
    * */
}
