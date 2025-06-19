package com.shoppingcart.emt.my_airbnb_backend.service.domain.impl;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.TemporaryReservation;
import com.shoppingcart.emt.my_airbnb_backend.model.exceptions.AlreadyInTemporaryReservationList;
import com.shoppingcart.emt.my_airbnb_backend.model.exceptions.AlreadyRentedException;
import com.shoppingcart.emt.my_airbnb_backend.repository.AccommodationRepository;
import com.shoppingcart.emt.my_airbnb_backend.repository.TemporaryReservationRepository;
import com.shoppingcart.emt.my_airbnb_backend.repository.UserRepository;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.TemporaryReservationService;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
@Service
public class TemporaryReservationServiceImpl implements TemporaryReservationService {

    private final TemporaryReservationRepository temporaryReservationRepository;
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    public TemporaryReservationServiceImpl(TemporaryReservationRepository temporaryReservationRepository, AccommodationRepository accommodationRepository, UserRepository userRepository) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<TemporaryReservation> addAccommodationToTempList(String username, Long accommodationId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));

        if (accommodation.isRented()) {
            throw new AlreadyRentedException("This accommodation is already rented");
        }

        if (temporaryReservationRepository.findByUserAndAccommodationId(user, accommodationId).isPresent()) {
            throw new AlreadyInTemporaryReservationList("This accommodation is already in the list");
        }

        TemporaryReservation reservation = new TemporaryReservation(user, accommodation);
        temporaryReservationRepository.save(reservation);
        return Optional.of(reservation);

    }

    @Override
    public void removeAccommodationFromTempList(String username, Long accommodationId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TemporaryReservation reservation = temporaryReservationRepository.findByUserAndAccommodationId(user, accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not in temporary list"));

        temporaryReservationRepository.delete(reservation);
    }

    @Override
    public List<TemporaryReservation> getUserTempList(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return temporaryReservationRepository.findAllByUser(user);
    }

    @Transactional
    @Override
    public Optional<List<TemporaryReservation>> confirmAllReservations(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<TemporaryReservation> reservations = temporaryReservationRepository.findAllByUser(user);

        if (reservations.isEmpty()) {
            throw new RuntimeException("No temporary reservations found for the user.");
        }

        for (TemporaryReservation r : reservations) {
            Accommodation acc = r.getAccommodation();
            if (!acc.isRented()) {
                acc.setRented(true);
                accommodationRepository.save(acc);
            }
        }

        // delete all reservations after confirming
        temporaryReservationRepository.deleteAllByUser(user);

        return Optional.of(reservations);
    }

}
