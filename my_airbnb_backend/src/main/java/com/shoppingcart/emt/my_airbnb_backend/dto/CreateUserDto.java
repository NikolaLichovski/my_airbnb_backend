package com.shoppingcart.emt.my_airbnb_backend.dto;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.User;
import com.shoppingcart.emt.my_airbnb_backend.model.enumerations.Role;

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
