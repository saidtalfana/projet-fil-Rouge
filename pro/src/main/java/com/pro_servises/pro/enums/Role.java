package com.pro_servises.pro.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    PROVIDER;

    @Override
    public String getAuthority() {
        return name();
    }
}
