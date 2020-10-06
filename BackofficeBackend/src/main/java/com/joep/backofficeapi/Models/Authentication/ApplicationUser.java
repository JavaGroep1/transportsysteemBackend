package com.joep.backofficeapi.Models.Authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ApplicationUser extends User {

    private final int Id;

    private final String email;
    private final String[] roles;
    public String profilePicture;

    public ApplicationUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int id, String email, String[] roles, String profilePicture) {
        super(username, password, authorities);
        Id = id;
        this.email = email;
        this.roles = roles;
        this.profilePicture = profilePicture;
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

    public String getProfilePicture() {return profilePicture;}

}
