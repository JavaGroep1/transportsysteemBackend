package com.joep.springsecurityjwt.DAL;

import com.joep.springsecurityjwt.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

public class UserStoreContainer {

    private IUserStore store;

    public UserStoreContainer(IUserStore userStore) {
        store = userStore;
    }

    public ArrayList<ApplicationUser> getAllUsers() {
        return store.getAllUsers();
    }

    public ApplicationUser getUserByName(String name) throws IOException {
        return store.getUserByName(name);
    }

    public Boolean createUser(ApplicationUser user) {
        return store.createUser(user);
    }

    public Boolean emailExists(String email) {
        return store.emailExists(email);
    }
}
