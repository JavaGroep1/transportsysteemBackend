package com.joep.backofficeapi.DAL.Interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Requests.Employee.EditEmployeeRequest;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.List;

public interface IUserStore {
    List<ApplicationUser> getAllUsers();
    ApplicationUser getUserByName(String name) throws Exception;
    ApplicationUser getUserById(ObjectId id) throws Exception;

    Boolean createUser(ApplicationUser user);
    Boolean emailExists(String email);
    Boolean usernameExists(String name);
    List<ApplicationUser> getByRole(Roles role);
    void changeRole(ObjectId customerId, Roles role);
    void deleteAccount(String businessIdentifier);
    void changeEmail(ObjectId customerId, String email);

    void deleteUser(ObjectId objectId);

    ApplicationUser updateEmployee(EditEmployeeRequest employee) throws Exception;
}
