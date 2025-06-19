package com.shoppingcart.emt.my_airbnb_backend.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_USER, ROLE_HOST;

    @Override
    public String getAuthority() {
        return name();
    }
}
