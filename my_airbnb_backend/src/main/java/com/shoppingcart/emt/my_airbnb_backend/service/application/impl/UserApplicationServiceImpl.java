package com.shoppingcart.emt.my_airbnb_backend.service.application.impl;

import com.shoppingcart.emt.my_airbnb_backend.dto.CreateUserDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayUserDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.LoginResponseDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.LoginUserDto;
import com.shoppingcart.emt.my_airbnb_backend.model.domain.User;
import com.shoppingcart.emt.my_airbnb_backend.security.JwtHelper;
import com.shoppingcart.emt.my_airbnb_backend.service.application.UserApplicationService;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }
}