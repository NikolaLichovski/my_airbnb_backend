package com.my_airbnb.repository;

import com.my_airbnb.model.domain.TemporaryReservation;
import com.my_airbnb.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation, Long> {
    List<TemporaryReservation> findAllByUser(User user);
    Optional<TemporaryReservation> findByUserAndAccommodationId(User user, Long accommodationId);
    void deleteAllByUser(User user);
}
