package com.my_airbnb.dto;

import com.my_airbnb.model.domain.Country;
import com.my_airbnb.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateHostDto(String name, String surname, Long country) {
    public static CreateHostDto from(Host host) {
        return new CreateHostDto(host.getName(), host.getSurname(), host.getCountry().getId());
    }

    public Host toHost(Country country) {
        return new Host(this.name, this.surname, country);
    }

    public static List<CreateHostDto> from(List<Host> hosts) {
        return hosts.stream().map(CreateHostDto::from).collect(Collectors.toList());
    }
}
