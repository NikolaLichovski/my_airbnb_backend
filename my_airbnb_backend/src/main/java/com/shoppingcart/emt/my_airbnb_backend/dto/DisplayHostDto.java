package com.shoppingcart.emt.my_airbnb_backend.dto;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Country;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDto(Long id, String name, String surname, Long country) {
    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(host.getId(), host.getName(), host.getSurname(), host.getCountry().getId());
    }

    public Host toHost(Country country) {
        return new Host(this.name, this.surname, country);
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }
}
