package com.joep.backofficeapi.DAL.Interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Requests.Employee.EditEmployeeRequest;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.List;

public interface IUserStore {
    public List<ApplicationUser> getAllUsers();
    public ApplicationUser getUserByName(String name) throws JsonProcessingException, IOException, Exception;
    public ApplicationUser getUserById(ObjectId id) throws JsonProcessingException, IOException, Exception;

    public Boolean createUser(ApplicationUser user);
    public Boolean emailExists(String email);
    public Boolean usernameExists(String name);
    public List<ApplicationUser> getByRole(Roles role);
    public void changeRole(ObjectId customerId, Roles role);
    public void deleteAccount(String businessIdentifier);
    public void changeEmail(ObjectId customerId, String email);

    void deleteUser(ObjectId objectId);

    ApplicationUser updateEmployee(EditEmployeeRequest employee) throws Exception;
}
