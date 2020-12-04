package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.ITicketStore;
import com.joep.backofficeapi.DAL.Interfaces.IUserStore;
import org.apache.catalina.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoreContainerTest {

    private UserStoreContainer container;
    private IUserStore userStore;

    @Before
    public void setup() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userStore = mock(IUserStore.class);
        container = new UserStoreContainer(userStore);
    }

    

}