package com.my_airbnb.dto;

import com.my_airbnb.model.domain.User;
import com.my_airbnb.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role

) {
    public User toUser() {
        return new User(username, password, name, surname, role);
    }

}
