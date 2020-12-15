package com.joep.backofficeapi.Util;

import com.joep.backofficeapi.Controllers.AuthenticationController;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Interfaces.IVehicleStore;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Requests.Auth.AuthenticationRequest;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JwtUtilTest {


    @Autowired
    private UserStoreContainer userDetailService;
    private AuthenticationController controller;
    private String token;
    private UserDetails details;
    @Before
    public void Setup() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userDetailService = mock(UserStoreContainer.class);
        var util = new JwtUtil();
        details = new ApplicationUser("username", "pass", "email@gmail.com", Roles.Customer);
        token=util.generateToken(details);
    }


    @Test
    public void UtilExtractsClaims() throws Exception {
        var util = new JwtUtil();
        var res=  util.extractUsername(token);
        assertEquals(res, "username");
    }

    @Test
    public void ThrowsExceptionWhenTokenExpired() throws Exception {
        var util = new JwtUtil();
        var expiredToken= "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjA0MzU5NzE2LCJpYXQiOjE2MDQzMjM3MTZ9.CqVfZAq1_JZaODCYDR_tfkyud0r8VEJ1L2giy8UDifI";
        assertThrows(ExpiredJwtException.class, () -> {
            util.isTokenExpired(expiredToken);
        });
    }
    @Test
    public void DoesNotThrowExceptionWithUserDetails(){
        //setup
        var util = new JwtUtil();
        //mocking
        when(userDetailService.loadUserByUsername("username")).thenReturn((ApplicationUser) details);
        //assert
        Assertions.assertDoesNotThrow(() -> {
            var res = util.validateToken(token, details);
        });
    }
}