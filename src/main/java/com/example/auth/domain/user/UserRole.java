package com.example.auth.domain.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    ORACAO("oracao"),
    SCHOOL("school");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}

