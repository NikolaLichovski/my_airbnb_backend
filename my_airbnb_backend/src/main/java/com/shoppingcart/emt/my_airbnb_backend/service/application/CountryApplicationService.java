package com.shoppingcart.emt.my_airbnb_backend.service.application;

import com.shoppingcart.emt.my_airbnb_backend.dto.CreateCountryDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto);

    void deleteById(Long id);

}
