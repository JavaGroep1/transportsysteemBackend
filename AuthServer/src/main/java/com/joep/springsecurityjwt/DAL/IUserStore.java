package com.joep.springsecurityjwt.DAL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joep.springsecurityjwt.models.ApplicationUser;

import java.io.IOException;
import java.util.ArrayList;

public interface IUserStore {
    public ArrayList<ApplicationUser> getAllUsers();
    public ApplicationUser getUserByName(String name) throws JsonProcessingException, IOException;
    public Boolean createUser(ApplicationUser user);
    public Boolean emailExists(String email);
}
