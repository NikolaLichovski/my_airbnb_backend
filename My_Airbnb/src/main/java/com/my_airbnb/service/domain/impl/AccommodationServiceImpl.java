package com.my_airbnb.service.domain.impl;

import com.my_airbnb.model.domain.Accommodation;
import com.my_airbnb.repository.AccommodationRepository;
import com.my_airbnb.service.domain.AccommodationService;
import com.my_airbnb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()){
            return Optional.of(accommodationRepository.save(
                    new Accommodation(accommodation.getName(), accommodation.getCategory(),
                            hostService.findById(accommodation.getHost().getId()).get(),
                            accommodation.getNumRooms())));
        }

        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id).map(existingAccommodation -> {
            if (accommodation.getName() != null){
                existingAccommodation.setName(accommodation.getName());
            }
            if (accommodation.getCategory() != null){
                existingAccommodation.setCategory(accommodation.getCategory());
            }
            if (accommodation.getNumRooms() != null){
                existingAccommodation.setNumRooms(accommodation.getNumRooms());
            }
            if (accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()){
                existingAccommodation.setHost(hostService.findById(accommodation.getHost().getId()).get());
            }

            return accommodationRepository.save(existingAccommodation);
        });
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return accommodationRepository.findById(id).map(existingAccommodation -> {
            existingAccommodation.setRented(true);
            return accommodationRepository.save(existingAccommodation);
        });
    }

    @Override
    public Optional<Accommodation> markAsFree(Long id) {
        return accommodationRepository.findById(id).map(existingAccommodation -> {
            existingAccommodation.setRented(false);
            return accommodationRepository.save(existingAccommodation);
        });
    }


}
