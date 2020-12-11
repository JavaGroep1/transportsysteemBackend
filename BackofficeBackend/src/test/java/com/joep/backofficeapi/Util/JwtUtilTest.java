package com.joep.backofficeapi.Util;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JwtUtilTest {

    private String getToken(String username, String pass) {
        return "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjA0MzU5NzE2LCJpYXQiOjE2MDQzMjM3MTZ9.CqVfZAq1_JZaODCYDR_tfkyud0r8VEJ1L2giy8UDifI";
    }

    public void UtilExtractsClaims() {
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