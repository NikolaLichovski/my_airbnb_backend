package com.shoppingcart.emt.my_airbnb_backend.service.application;

import com.shoppingcart.emt.my_airbnb_backend.dto.*;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
