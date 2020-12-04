package com.joep.backofficeapi.DAL.Containers;


import com.joep.backofficeapi.DAL.Interfaces.IUserStore;
import com.joep.backofficeapi.Exceptions.EmailTakenException;
import com.joep.backofficeapi.Exceptions.InvalidEmailException;
import com.joep.backofficeapi.Exceptions.UsernameTakenException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Employee.EditEmployeeRequest;
import com.joep.backofficeapi.Util.Sanitizers.CustomerSanitizer;
import com.joep.backofficeapi.Util.Sanitizers.EmployeeSanitizer;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserStoreContainer implements UserDetailsService {

    @Autowired
    private CustomerContainer customerContainer;

    private IUserStore store;

    public UserStoreContainer(IUserStore userStore) {
        store=userStore;
    }

    public List<ApplicationUser> getAllUsers(){
        return store.getAllUsers();
    }
    public ApplicationUser getUserByName(String name) throws Exception {
        return store.getUserByName(name);
    }
    public Boolean createUser(ApplicationUser user) throws Exception {
        if (!isValidEmail(user.getEmail())){
            throw new InvalidEmailException();
        }
        if (emailExists(user.getEmail())) {
            throw new EmailTakenException();
        }
        if (usernameExists(user.getUsername())) {
            throw new UsernameTakenException();
        }
        if (user.getCustomer() != null){
            customerContainer.addCustomer(user.getCustomer());
        }
        return store.createUser(user);
    }
    public Boolean emailExists(String email){
        return store.emailExists(email);
    }
    public Boolean usernameExists(String user){
        return store.usernameExists(user);
    }

    private boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public ApplicationUser loadUserByUsername(String s) {
        try {
            return getUserByName(s);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ApplicationUser> getByRole(Roles role) {
        return store.getByRole(role);

    }

    public void changeRole(ObjectId customerId, Roles role) {
        store.changeRole(customerId, role);
    }

    public void deleteAccountByBusinessId(String businessIdentifier) {
        store.deleteAccount(businessIdentifier);
    }

    public void changeEmail(ObjectId customerIdString, String email) {
        store.changeEmail(customerIdString, email);
    }
    public ApplicationUser getUserById(ObjectId id) throws Exception {
        return store.getUserById(id);

    }

    public void deleteUser(ObjectId objectId) {
        store.deleteUser(objectId);
    }

    public ApplicationUser updateEmployee(EditEmployeeRequest employee) throws Exception {
        ApplicationUser user = store.getUserById(new ObjectId(employee.getIdString()));
        EmployeeSanitizer.sanitize(employee, user);
        return store.updateEmployee(employee);
    }
}
