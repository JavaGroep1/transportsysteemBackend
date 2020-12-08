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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class RoleAuthorizationTest {

    RoleAuthorization roleAuthorization;
    @Before
    public void Setup() throws Exception {
        var userstore = mock(UserStoreContainer.class);
        var user = new ApplicationUser("user", "pass", "email", Roles.Employee);
        when(userstore.getUserByName(any())).thenReturn(user);
        roleAuthorization = new RoleAuthorization(userstore);
    }

    @Test
    public void unauthorizedUserThrowsUnauthorizedException () throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var request = mock(HttpServletRequest.class);
        when(request.getHeader(anyString())).thenReturn("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjA5NDk5ODU5LCJpYXQiOjE2MDczNTIzNzV9.2_ZfYXAFhTx6gvEuaL8Aqzqp5Cu6RBxAO-dGECA2FWc");
        //execute
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            roleAuthorization.checkRole(request, new Roles[]{Roles.Admin});
        });
    }

    @Test
    public void authorizedUserDoesNotThrowUnauthorizedException () throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var request = mock(HttpServletRequest.class);
        when(request.getHeader(anyString())).thenReturn("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjA5NDk5ODU5LCJpYXQiOjE2MDczNTIzNzV9.2_ZfYXAFhTx6gvEuaL8Aqzqp5Cu6RBxAO-dGECA2FWc");
        //execute
        Assertions.assertDoesNotThrow(() -> {
            roleAuthorization.checkRole(request, new Roles[]{Roles.Employee});
        });
    }

    @Test
    public void authorizedUserReturnsTrue () throws Exception {
        //setup
        var request = mock(HttpServletRequest.class);
        when(request.getHeader(anyString())).thenReturn("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjA5NDk5ODU5LCJpYXQiOjE2MDczNTIzNzV9.2_ZfYXAFhTx6gvEuaL8Aqzqp5Cu6RBxAO-dGECA2FWc");

        //execute
        Assert.assertTrue(roleAuthorization.checkRole(request, new Roles[]{Roles.Employee}));
    }

}