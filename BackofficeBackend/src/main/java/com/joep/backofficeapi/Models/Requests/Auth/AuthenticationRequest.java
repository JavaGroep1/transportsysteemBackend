package com.joep.backofficeapi.Models.Requests.Auth;

import com.joep.backofficeapi.Models.Authentication.Roles;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationRequest {
    private String username;
    private String email;
    private Roles role;
    public AuthenticationRequest(String username, String email, Roles role, String password) {
        this.username = username;
        this.email = email;
        this.role = role;
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

    public Roles getRole() {
        return role;
    }
}
