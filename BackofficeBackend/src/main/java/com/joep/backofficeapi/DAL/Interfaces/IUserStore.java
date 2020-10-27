package com.joep.backofficeapi.DAL.Interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;

import java.io.IOException;
import java.util.List;

public interface IUserStore {
    public List<ApplicationUser> getAllUsers();
    public ApplicationUser getUserByName(String name) throws JsonProcessingException, IOException, Exception;
    public Boolean createUser(ApplicationUser user);
    public Boolean emailExists(String email);
    public Boolean usernameExists(String name);
    public List<ApplicationUser> getByRole(Roles role);

}
