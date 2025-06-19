package com.shoppingcart.emt.my_airbnb_backend.dto;

import com.shoppingcart.emt.my_airbnb_backend.model.enumerations.AccommodationCategory;

public record HostAndCategoryDto(AccommodationCategory category, String hostName,
                                 String hostSurname, String hostCountryName, String hostCountryContinent) {
}
