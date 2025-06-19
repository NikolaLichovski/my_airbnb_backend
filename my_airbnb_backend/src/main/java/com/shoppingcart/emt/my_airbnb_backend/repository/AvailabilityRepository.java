package com.shoppingcart.emt.my_airbnb_backend.repository;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByAccommodationId(Long accommodationId);
}
