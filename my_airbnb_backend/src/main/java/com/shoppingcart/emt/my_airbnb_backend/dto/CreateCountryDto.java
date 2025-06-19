package com.shoppingcart.emt.my_airbnb_backend.dto;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCountryDto(String name, String continent) {
    public static CreateCountryDto from(Country country) {
        return new CreateCountryDto(country.getName(), country.getContinent());
    }

    public Country toCountry() {
        return new Country(name, continent);
    }

    public List<CreateCountryDto> from(List<Country> countries) {
        return countries.stream().map(CreateCountryDto::from).collect(Collectors.toList());
    }
}
