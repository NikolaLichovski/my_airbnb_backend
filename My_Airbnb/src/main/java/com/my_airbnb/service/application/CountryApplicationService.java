package com.my_airbnb.service.application;

import com.my_airbnb.dto.CreateCountryDto;
import com.my_airbnb.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto);

    void deleteById(Long id);

}
