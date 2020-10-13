package com.joep.backofficeapi.Models.Requests.Auth;

import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import org.bson.types.ObjectId;

public class UserInfoResponse {
    private final String  username;
    private final ObjectId id;
    private final Roles role;
    private final String email;
    private final String profilePicture;
    public String getUsername() {
        return username;
    }

    public ObjectId getId() {
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

    public UserInfoResponse(ObjectId id, String username, String email, Roles role, String profilePicture) {
        this.username = username;
        this.id = id;
        this.role = role;
        this.email = email;
        this.profilePicture = profilePicture;
    }
}
