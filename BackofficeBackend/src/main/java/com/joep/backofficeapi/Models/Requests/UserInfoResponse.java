package com.joep.backofficeapi.Models.Requests;

import com.joep.backofficeapi.Models.Authentication.ApplicationUser;

public class UserInfoResponse {
    private final String  username;
    private final Integer  id;
    private final String[] roles;
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

    public String[] getRoles(){
        return roles;
    }
    public String getProfilePicture() {return profilePicture;}

    public UserInfoResponse(ApplicationUser user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.id = user.getId();
        this.profilePicture = user.getProfilePicture();
    }

    public UserInfoResponse(Integer id, String username, String email, String[] roles, String profilePicture) {
        this.username = username;
        this.id = id;
        this.roles = roles;
        this.email = email;
        this.profilePicture = profilePicture;
    }
}
