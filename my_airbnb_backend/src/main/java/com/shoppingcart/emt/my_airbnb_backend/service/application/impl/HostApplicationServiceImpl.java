package com.shoppingcart.emt.my_airbnb_backend.service.application.impl;

import com.shoppingcart.emt.my_airbnb_backend.dto.CreateHostDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayHostDto;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.Country;
import com.shoppingcart.emt.my_airbnb_backend.model.projections.HostNameProjection;
import com.shoppingcart.emt.my_airbnb_backend.model.views.HostsPerCountryView;
import com.shoppingcart.emt.my_airbnb_backend.service.application.HostApplicationService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.CountryService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto createHostDto) {
        Optional<Country> country = countryService.findById(createHostDto.country());
        if (country.isPresent()) {
            return hostService.save(createHostDto.toHost(country.get())).map(DisplayHostDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto) {
        Optional<Country> country = countryService.findById(createHostDto.country());

        return hostService.update(id, createHostDto.toHost(country.orElse(null))).map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public List<HostsPerCountryView> getHostsPerCountry() {
        return hostService.getHostsPerCountry();
    }

    @Override
    public List<HostNameProjection> showAllHostNamesByProjection() {
        return hostService.showAllHostNamesByProjection();
    }
}
