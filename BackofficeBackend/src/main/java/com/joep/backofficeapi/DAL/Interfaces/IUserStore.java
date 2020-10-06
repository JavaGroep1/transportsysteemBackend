package com.joep.backofficeapi.DAL.Interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;

import java.io.IOException;
import java.util.ArrayList;

public interface IUserStore {
    public ArrayList<ApplicationUser> getAllUsers();
    public ApplicationUser getUserByName(String name) throws JsonProcessingException, IOException;
    public Boolean createUser(ApplicationUser user);
    public Boolean emailExists(String email);
    public Boolean usernameExists(String name);
}
