package com.example.auth.domain.user;

import jakarta.validation.constraints.NotBlank;

public class AuthenticationDTO {
    @NotBlank(message = "O campo 'login' é obrigatório.")
    private String login;

    @NotBlank(message = "O campo 'password' é obrigatório.")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
