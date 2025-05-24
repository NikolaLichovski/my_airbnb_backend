package com.my_airbnb.service.domain;

import com.my_airbnb.model.domain.User;
import com.my_airbnb.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);

}
