package com.joep.backofficeapi.Models.Requests.Auth;

import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;

public class UserInfoResponse {
    private final String  username;
    private final Integer  id;
    private final Roles role;
    private final String email;
    private final String profilePicture;
    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Roles getRole(){
        return role;
    }
    public String getProfilePicture() {return profilePicture;}

    public UserInfoResponse(ApplicationUser user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.id = user.getId();
        this.profilePicture = user.getProfilePicture();
    }

    public UserInfoResponse(Integer id, String username, String email, Roles role, String profilePicture) {
        this.username = username;
        this.id = id;
        this.role = role;
        this.email = email;
        this.profilePicture = profilePicture;
    }
}
