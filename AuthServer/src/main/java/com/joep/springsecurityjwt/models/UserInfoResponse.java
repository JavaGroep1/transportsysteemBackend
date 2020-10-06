package com.joep.springsecurityjwt.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class UserInfoResponse {
    private final String username;
    private final Integer id;
    private final String[] roles;
    private final String email;

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String[] getRoles() {
        return roles;
    }


    public UserInfoResponse(ApplicationUser user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.id = user.getId();
    }

    public UserInfoResponse(Integer id, String username, String email, String[] roles) {
        this.username = username;
        this.id = id;
        this.roles = roles;
        this.email = email;
    }
}
