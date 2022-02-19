package com.java.SpringSecurityRestAPI.model;

public enum Permission {

    USERS_READ("users:read"),
    USERS_WRITE("users:write"),

    EVENT_READ("events:read"),
    EVENT_WRITE("events:write"),

    FILE_READ("files:read"),
    FILE_WRITE("files:write");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
