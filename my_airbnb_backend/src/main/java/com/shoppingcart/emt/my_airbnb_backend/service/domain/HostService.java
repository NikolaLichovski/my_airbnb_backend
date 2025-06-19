package com.shoppingcart.emt.my_airbnb_backend.service.domain;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Host;
import com.shoppingcart.emt.my_airbnb_backend.model.projections.HostNameProjection;
import com.shoppingcart.emt.my_airbnb_backend.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);

    Optional<Host> save(Host host);
    Optional<Host> update(Long id, Host host);

    void deleteById(Long id);

    List<HostsPerCountryView> getHostsPerCountry();
    void refreshMaterializedView();

    List<HostNameProjection> showAllHostNamesByProjection();


}
