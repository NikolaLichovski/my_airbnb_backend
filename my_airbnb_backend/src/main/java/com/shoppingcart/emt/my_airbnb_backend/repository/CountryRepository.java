package com.shoppingcart.emt.my_airbnb_backend.repository;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
