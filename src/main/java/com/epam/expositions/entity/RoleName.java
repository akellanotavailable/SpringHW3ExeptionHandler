package com.epam.expositions.entity;

public enum RoleName {
    GUEST("guest"), USER("user"), ADMIN("admin");
    private final String role;

    RoleName(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return role;
    }

}

