package com.joep.springsecurityjwt.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ApplicationUser extends User {

    private final int Id;

    private final String email;
    private final String[] roles;

    public ApplicationUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int id, String email, String[] roles) {
        super(username, password, authorities);
        Id = id;
        this.email = email;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return Id;
    }

    public String[] getRoles() {
        return roles;
    }
}
