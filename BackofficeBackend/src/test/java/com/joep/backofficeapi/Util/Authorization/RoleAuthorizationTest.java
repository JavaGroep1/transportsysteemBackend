package com.joep.backofficeapi.Util.Authorization;

import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Interfaces.IUserStore;
import com.joep.backofficeapi.Exceptions.UnauthorizedException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleAuthorizationTest {

    private RoleAuthorization roleAuthorization;
    @Before
    public void Setup(){
        var userStore = mock(IUserStore.class);
        roleAuthorization= new RoleAuthorization(new UserStoreContainer(userStore));
    }

    @Test
    public void unauthorizedUserThrowsUnauthorizedException () throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var user  = new ApplicationUser("user", "pass", "email", Roles.Employee);

        //execute
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            roleAuthorization.checkRole(user, new Roles[]{Roles.Admin});
        });
    }

    @Test
    public void authorizedUserDoesNotThrowUnauthorizedException () throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var user  = new ApplicationUser("user", "pass", "email", Roles.Admin);

        //execute
        Assertions.assertDoesNotThrow(() -> {
            roleAuthorization.checkRole(user, new Roles[]{Roles.Admin});
        });
    }

    @Test
    public void authorizedUserReturnsTrue () throws Exception {
        //setup
        var user  = new ApplicationUser("user", "pass", "email", Roles.Admin);

        //execute
        Assert.assertTrue(roleAuthorization.checkRole(user, new Roles[]{user.getRole()}));
    }

}