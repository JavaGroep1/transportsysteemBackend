package com.joep.backofficeapi.Models.Authentication;

import com.joep.backofficeapi.Models.Customer;
import dev.morphia.annotations.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ApplicationUser extends User {

    private final int Id;

    private final String email;
    private final Roles role;
    public String profilePicture;

    @Reference
    private Customer customer;

    public ApplicationUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int id, String email, Roles role, String profilePicture, Customer customer) {
        super(username, password, authorities);
        Id = id;
        this.email = email;
        this.role = role;
        this.profilePicture = profilePicture;
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return Id;
    }

    public Roles getRole() {
        return role;
    }

    public String getProfilePicture() {return profilePicture;}

    public Customer getCustomer() {
        return customer;
    }
}
