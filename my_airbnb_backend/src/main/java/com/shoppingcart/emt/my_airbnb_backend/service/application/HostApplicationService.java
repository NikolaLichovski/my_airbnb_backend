package com.shoppingcart.emt.my_airbnb_backend.service.application;

import com.shoppingcart.emt.my_airbnb_backend.dto.CreateHostDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayHostDto;
import com.shoppingcart.emt.my_airbnb_backend.model.projections.HostNameProjection;
import com.shoppingcart.emt.my_airbnb_backend.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {

    List<DisplayHostDto> findAll();
    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> save(CreateHostDto createHostDto);
    Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto);

    void deleteById(Long id);

    List<HostsPerCountryView> getHostsPerCountry();
    List<HostNameProjection> showAllHostNamesByProjection();


}
