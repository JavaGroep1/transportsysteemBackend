package com.joep.springsecurityjwt.models;

public class UserInfoRequest {
    private final String authToken;

    public UserInfoRequest(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}
