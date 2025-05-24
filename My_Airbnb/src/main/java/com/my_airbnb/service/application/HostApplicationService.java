package com.my_airbnb.service.application;

import com.my_airbnb.dto.CreateHostDto;
import com.my_airbnb.dto.DisplayHostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {

    List<DisplayHostDto> findAll();
    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> save(CreateHostDto createHostDto);
    Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto);

    void deleteById(Long id);
}
