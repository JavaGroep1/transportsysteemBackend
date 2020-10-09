package com.joep.backofficeapi.Models.Requests.Auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;


public class AuthenticationResponse {
    private final String accessToken;

    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
