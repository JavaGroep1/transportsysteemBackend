package com.joep.backofficeapi.Util.Authorization;

import com.joep.backofficeapi.Exceptions.UnauthorizedException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Assertions;

public class RoleAuthorizationTest {
    @Test
    public void unauthorizedUserThrowsUnauthorizedException () throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var user  = new ApplicationUser("user", "pass", "email", Roles.User);

        //execute
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            RoleAuthorization.checkRole(user, new Roles[]{Roles.Admin});
        });
    }

    @Test
    public void authorizedUserDoesNotThrowUnauthorizedException () throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var user  = new ApplicationUser("user", "pass", "email", Roles.Admin);

        //execute
        Assertions.assertDoesNotThrow(() -> {
            RoleAuthorization.checkRole(user, new Roles[]{Roles.Admin});
        });
    }

    @Test
    public void authorizedUserReturnsTrue () throws Exception {
        //setup
        var user  = new ApplicationUser("user", "pass", "email", Roles.Admin);

        //execute
        Assert.assertTrue(RoleAuthorization.checkRole(user, new Roles[]{user.getRole()}));
    }

}