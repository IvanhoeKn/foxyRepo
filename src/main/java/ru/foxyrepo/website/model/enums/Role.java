package ru.foxyrepo.website.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_READER, ROLE_WRITER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
