package com.joep.springsecurityjwt.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationRequest {
    private String username;
    private String email;
    private String[] roles;
    public AuthenticationRequest(String username, String email, String[] roles, String password) {
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public AuthenticationRequest() {
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String[] getRoles() {
        return roles;
    }
}
