package com.java.SpringSecurityRestAPI.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.EVENT_READ, Permission.FILE_READ)),

    MODERATOR(Set.of(Permission.USERS_READ, Permission.EVENT_READ, Permission.FILE_READ, Permission.FILE_WRITE)),

    ADMIN(Set.of(Permission.USERS_READ, Permission.USERS_WRITE, Permission.EVENT_READ, Permission.EVENT_WRITE,
            Permission.FILE_READ, Permission.FILE_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthority() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
