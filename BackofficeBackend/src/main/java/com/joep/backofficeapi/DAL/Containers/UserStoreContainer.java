package com.joep.backofficeapi.DAL.Containers;


import com.joep.backofficeapi.DAL.Interfaces.IUserStore;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;

import java.io.IOException;
import java.util.ArrayList;

public class UserStoreContainer {

    private IUserStore store;

    public UserStoreContainer(IUserStore userStore) {
        store=userStore;
    }

    public ArrayList<ApplicationUser> getAllUsers(){
        return store.getAllUsers();
    }
    public ApplicationUser getUserByName(String name) throws IOException {
        return store.getUserByName(name);
    }
    public Boolean createUser(ApplicationUser user){
        return store.createUser(user);
    }
    public Boolean emailExists(String email){
        return store.emailExists(email);
    }
    public Boolean usernameExists(String user){
        return store.usernameExists(user);
    }
}
