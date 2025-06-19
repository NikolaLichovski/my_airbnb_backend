package com.shoppingcart.emt.my_airbnb_backend.service.domain.impl;

import com.shoppingcart.emt.my_airbnb_backend.events.HostModifiedEvent;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.Host;
import com.shoppingcart.emt.my_airbnb_backend.model.projections.HostNameProjection;
import com.shoppingcart.emt.my_airbnb_backend.model.views.HostsPerCountryView;
import com.shoppingcart.emt.my_airbnb_backend.repository.HostRepository;
import com.shoppingcart.emt.my_airbnb_backend.repository.HostsPerCountryViewRepository;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.CountryService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, HostsPerCountryViewRepository hostsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(Host host) {
        if (host.getCountry() != null &&
            countryService.findById(host.getCountry().getId()).isPresent()){
            this.applicationEventPublisher.publishEvent(new HostModifiedEvent(host));
            return Optional.of(hostRepository.save(new Host(host.getName(), host.getSurname(), countryService.findById(host.getCountry().getId()).get())));
        }

        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id).map(existingHost -> {
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()){
                existingHost.setCountry(countryService.findById(host.getCountry().getId()).get());
            }

            this.applicationEventPublisher.publishEvent(new HostModifiedEvent(host));
            return hostRepository.save(existingHost);
        });
    }

    @Override
    public void deleteById(Long id) {
        Optional<Host> host = hostRepository.findById(id);
        hostRepository.deleteById(id);
        host.ifPresent(h -> applicationEventPublisher.publishEvent(new HostModifiedEvent(h)));

    }

    @Override
    public List<HostsPerCountryView> getHostsPerCountry() {
        return hostsPerCountryViewRepository.findAll();
    }

    @Override
    public void refreshMaterializedView() {
        hostsPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostNameProjection> showAllHostNamesByProjection() {
        return hostRepository.findAllHostNames();
    }
}
