package com.shoppingcart.emt.my_airbnb_backend.service.domain.impl;

import com.shoppingcart.emt.my_airbnb_backend.dto.AccommodationDetailsDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.HostAndCategoryDto;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.Accommodation;
import com.shoppingcart.emt.my_airbnb_backend.model.views.AccommodationsPerHostView;
import com.shoppingcart.emt.my_airbnb_backend.repository.AccommodationRepository;
import com.shoppingcart.emt.my_airbnb_backend.repository.AccommodationsPerHostRepository;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.AccommodationService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final AccommodationsPerHostRepository accommodationsPerHostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, AccommodationsPerHostRepository accommodationsPerHostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.accommodationsPerHostRepository = accommodationsPerHostRepository;
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

    @Override
    public List<AccommodationsPerHostView> getAccommodationsPerHost() {
        return accommodationsPerHostRepository.findAll();
    }

    @Override
    public void refreshMaterializedView() {
        accommodationsPerHostRepository.refreshMaterializedView();
    }


    @Override
    public Optional<AccommodationDetailsDto> getAccommodationDetails(Long id) {
        return accommodationRepository.findById(id).map(accommodation -> {
            String hostName = accommodation.getHost().getName();
            String hostSurname = accommodation.getHost().getSurname();
            String countryName = accommodation.getHost().getCountry().getName();
            String countryContinent = accommodation.getHost().getCountry().getContinent();

            HostAndCategoryDto hostAndCategoryDto = new HostAndCategoryDto(
                    accommodation.getCategory(),hostName,hostSurname,countryName,countryContinent);

            return new AccommodationDetailsDto(
                    accommodation.getId(),
                    accommodation.getName(),
                    accommodation.getNumRooms(),
                    hostAndCategoryDto
            );
        });
    }


}
