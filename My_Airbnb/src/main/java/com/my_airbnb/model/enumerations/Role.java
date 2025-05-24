package com.my_airbnb.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_USER, ROLE_HOST;

    @Override
    public String getAuthority() {
        return name();
    }
}
