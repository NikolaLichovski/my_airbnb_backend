package com.shoppingcart.emt.my_airbnb_backend.dto;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDto(Long id, String name, String continent) {
    public static DisplayCountryDto from(Country country) {
        return new DisplayCountryDto(country.getId(), country.getName(), country.getContinent());
    }

    public Country toCountry() {
        return new Country(this.name, this.continent);
    }

    public static List<DisplayCountryDto> from(List<Country> countries) {
        return countries.stream().map(DisplayCountryDto::from).collect(Collectors.toList());
    }
}
