package com.joep.backofficeapi.Models.Authentication;

import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Util.PasswordEncoder;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;
import dev.morphia.annotations.Transient;
import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

@Entity
public class ApplicationUser implements UserDetails {

    public ApplicationUser() {
    }

    @Transient
    private PasswordEncoder encoder = new PasswordEncoder();

    @dev.morphia.annotations.Id
    private ObjectId Id;

    private String passwordHash;
    private String username;

    private String email;
    private Roles role;
    public String profilePicture;

    @Reference
    private Customer customer;

    public ApplicationUser(String username, String password, String email, Roles role, Customer customer) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        this.username = username;
        this.customer = customer;
        this.passwordHash = encoder.Encode(password);
        this.email = email;
        this.role = role;
        this.profilePicture = "https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png";

    }


    public String getEmail() {
        return email;
    }

    public ObjectId getId() {
        return Id;
    }

    public Roles getRole() {
        return role;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Customer getCustomer() {
        return customer;
    }
}