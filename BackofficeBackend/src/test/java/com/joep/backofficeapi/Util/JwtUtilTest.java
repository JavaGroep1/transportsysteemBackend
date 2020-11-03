package com.joep.backofficeapi.Util;

import com.joep.backofficeapi.Controllers.AuthenticationController;
import com.joep.backofficeapi.Models.Requests.Auth.AuthenticationRequest;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    @Autowired
    private AuthenticationController controller;

    private String getToken(String username, String pass) throws Exception {
      // var token = controller.createAuthenticationToken(new AuthenticationRequest(username, pass)).getStatusCode().getReasonPhrase();
        return "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjA0MzU5NzE2LCJpYXQiOjE2MDQzMjM3MTZ9.CqVfZAq1_JZaODCYDR_tfkyud0r8VEJ1L2giy8UDifI";
    }
    @Test
    public void UtilExtractsClaims() throws Exception {
        var util = new JwtUtil();
        var token = getToken("user", "d");
        var res=   util.extractUsername(token);
        assertEquals(res, "user");
    }

    @Test
    public void ThrowsExceptionWhenTokenExpired() throws Exception {
        var util = new JwtUtil();
        var expiredToken= "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjA0MzU5NzE2LCJpYXQiOjE2MDQzMjM3MTZ9.CqVfZAq1_JZaODCYDR_tfkyud0r8VEJ1L2giy8UDifI";
        assertThrows(ExpiredJwtException.class, () -> {
            util.isTokenExpired(expiredToken);
        });
    }
}