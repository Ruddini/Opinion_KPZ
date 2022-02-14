package com.example.hotelopinionapp.model;

public enum UserRole {

    ADMIN("Administrator"),
    USER("Użytkownik");

    private final String polishName;

    UserRole(String polishName) {
        this.polishName = polishName;
    }

    public String getPolishName() {
        return polishName;
    }
}
