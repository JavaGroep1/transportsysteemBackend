package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.IUserStore;
import com.joep.backofficeapi.Exceptions.EmailTakenException;
import com.joep.backofficeapi.Exceptions.InvalidEmailException;
import com.joep.backofficeapi.Exceptions.UserNotFoundException;
import com.joep.backofficeapi.Exceptions.UsernameTakenException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Employee.EditEmployeeRequest;
import com.joep.backofficeapi.Util.JwtUtil;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserStoreContainerTest {

    private UserStoreContainer container;
    private IUserStore userStore;
    private CustomerContainer customerContainer;

    @Before
    public void setup() {
        userStore = mock(IUserStore.class);
        container = new UserStoreContainer(userStore);
        customerContainer = mock(CustomerContainer.class);
        container = new UserStoreContainer(userStore, customerContainer);
    }

    @Test
    public void getUsersReturnsList(){
        //setup
        var listOfUsers = new ArrayList<ApplicationUser>();
        listOfUsers.add(new ApplicationUser());
        listOfUsers.add(new ApplicationUser());

        //Mocking
        when(userStore.getAllUsers()).thenReturn(listOfUsers);

        //execute
        var res = container.getAllUsers();

        //assert
        assertEquals(res, listOfUsers);
    }

    @Test
    public void getUserByNameReturnsUser() throws Exception {
        //setup
        var query = "username";
        var userToReturn = new ApplicationUser();

        //Mocking
        when(userStore.getUserByName(query)).thenReturn(userToReturn);

        //execute
        var res = container.getUserByName(query);

        //assert
        assertEquals(res, userToReturn);
    }

    @Test
    public void getUserByNameReturnsUserNotFound() throws Exception {
        //setup
        var query = "username";

        //Mocking
        when(userStore.getUserByName(query)).thenReturn(null);

        //assert
        assertThrows(UserNotFoundException.class, () -> {
            //execute
            var res = container.getUserByName(query); });
    }

    @Test
    public void LoadUserByNameReturnsUser() throws Exception {
        //setup
        var query = "username";
        var userToReturn = new ApplicationUser();

        //Mocking
        when(userStore.getUserByName(query)).thenReturn(userToReturn);

        //execute
        var res = container.loadUserByUsername(query);

        //assert
        assertEquals(res, userToReturn);
    }


    @Test
    public void LoadUserByNameReturnsUserReturnsNull() throws Exception {
        //setup
        var query = "username";
        var userToReturn = new ApplicationUser();

        //Mocking
        when(userStore.getUserByName(query)).thenReturn(null);

        //execute
        var res = container.loadUserByUsername(query);

        //assert
        assertNull(res);
    }

    @Test
    public void canCreateUser() throws Exception {
        //setup
        var userToCreate = new ApplicationUser("username", "pass", "email@gmail.com", Roles.Unknown);
        //Mocking
        when(userStore.usernameExists(anyString())).thenReturn(false);
        when(userStore.emailExists(anyString())).thenReturn(false);


        //assert
        assertDoesNotThrow(() -> {
            //execute
            var res = container.createUser(userToCreate);
        });
    }

    @Test
    public void canCreateUserWithCustomer() throws Exception {
        //setup
        var customerToReturn = new Customer();
        var userToCreate = new ApplicationUser("username", "pass", "email@gmail.com", Roles.Unknown);
        userToCreate.setCustomer(customerToReturn);
        //Mocking
        when(userStore.usernameExists(anyString())).thenReturn(false);
        when(userStore.emailExists(anyString())).thenReturn(false);


        //assert
        assertDoesNotThrow(() -> {
            //execute
            var res = container.createUser(userToCreate);
        });
    }


    @Test
    public void takenEmailShowsError() throws Exception {
        //setup
        var userToCreate = new ApplicationUser("username", "pass", "email@gmail.com", Roles.Unknown);
        //Mocking
        when(userStore.usernameExists(anyString())).thenReturn(false);
        when(userStore.emailExists(anyString())).thenReturn(true);


        //assert
        assertThrows(EmailTakenException.class,() -> {
            //execute
            var res = container.createUser(userToCreate); });
    }

    @Test
    public void takenUsernameShowsError() throws Exception {
        //setup
        var userToCreate = new ApplicationUser("username", "pass", "email@gmail.com", Roles.Unknown);
        //Mocking
        when(userStore.usernameExists(anyString())).thenReturn(true);
        when(userStore.emailExists(anyString())).thenReturn(false);


        //assert
        assertThrows(UsernameTakenException.class,() -> {
            //execute
            var res = container.createUser(userToCreate); });
    }

    @Test
    public void invalidEmailShowsException() throws Exception {
        //setup
        var userToCreate = new ApplicationUser("username", "pass", "email.com", Roles.Unknown);
        //Mocking
        when(userStore.usernameExists(anyString())).thenReturn(false);
        when(userStore.emailExists(anyString())).thenReturn(false);

        //assert
        assertThrows(InvalidEmailException.class,() -> {
            //execute
            var res = container.createUser(userToCreate); });
    }

    @Test
    public void LoadByRoleReturnsUsers() {
        //setup
        var query = Roles.Customer;
        var usersToReturn = new ArrayList<ApplicationUser>();
        usersToReturn.add(new ApplicationUser());
        usersToReturn.add(new ApplicationUser());


        //Mocking
        when(userStore.getByRole(query)).thenReturn(usersToReturn);

        //execute
        var res = container.getByRole(query);

        //assert
        assertEquals(res, usersToReturn);
    }

    @Test
    public void canChangeRole() {
        //setup
        var newRole = Roles.Customer;
        var user = new ApplicationUser();



        //assert
        assertDoesNotThrow(() -> {
            //execute
            container.changeRole(user.getId(),newRole);
        });
    }

    @Test
    public void canChangeEmail() {
        //setup
        var newEmail ="newEmail@gmail.com";
        var user = new ApplicationUser();

        //assert
        assertDoesNotThrow(() -> {
            //execute
            container.changeEmail(user.getId(),newEmail);
        });
    }

    @Test
    public void cantChangeWithTakenEmail() {
        //setup
        var newEmail ="newEmail@gmail.com";
        var user = new ApplicationUser();

        //Mocking
        when(userStore.emailExists(anyString())).thenReturn(true);

        //assert
        assertThrows(EmailTakenException.class,() -> {
            //execute
            container.changeEmail(user.getId(),newEmail); });

    }

    @Test
    public void cantChangeWithInvalidEmail() {
        //setup
        var newEmail ="fakeemail";
        var user = new ApplicationUser();

        //Mocking
        when(userStore.emailExists(anyString())).thenReturn(false);

        //assert
        assertThrows(InvalidEmailException.class,() -> {
            //execute
            container.changeEmail(user.getId(),newEmail); });

    }

    @Test
    public void canDeleteByBusinessId(){
        //setup
        var businessId = "businessId";

        //execute
        container.deleteAccountByBusinessId(businessId);
    }

    @Test
    public void canDelete(){
        var id = new ObjectId();
        //execute
        container.deleteUser(id);
    }

    @Test
    public void getUserByIdReturnsUser() throws Exception {
        //setup
        var query = new ObjectId();
        var userToReturn = new ApplicationUser();

        //Mocking
        when(userStore.getUserById(query)).thenReturn(userToReturn);

        //execute
        var res = container.getUserById(query);

        //assert
        assertEquals(res, userToReturn);
    }

    @Test
    public void getUserByIdReturnsUserNotFound() throws Exception {
        //setup
        var query = new ObjectId();

        //Mocking
        when(userStore.getUserById(query)).thenReturn(null);

        //assert
        assertThrows(UserNotFoundException.class, () -> {
            //execute
            var res = container.getUserById(query); });
    }

    @Test
    public void canUpdateEmployee() throws Exception {
        //setup
        var editRequest = new EditEmployeeRequest(new ObjectId().toString(), "newusername", "newfirstname", "newlastname", "newEmail@gmail.com");
        var user = new ApplicationUser("user", "pass", "valid@gmail.com", Roles.Unknown);
        //mocking
        when(userStore.getUserById(any())).thenReturn(user);

        //execute
        container.updateEmployee(editRequest);
    }

    @Test
    public void canUpdateEmployeeWithNulls() throws Exception {
        //setup
        var editRequest = new EditEmployeeRequest(new ObjectId().toString(), "", "", "", "");
        var user = new ApplicationUser("user", "pass", "valid@gmail.com", Roles.Unknown);
        //mocking
        when(userStore.getUserById(any())).thenReturn(user);

        //execute
        container.updateEmployee(editRequest);
    }
    @Test
    public void cantUpdateEmployeeWithTakenEmail() throws Exception {
        //setup
        var editRequest = new EditEmployeeRequest(new ObjectId().toString(), "", "", "", "validbuttaken@gmail.com");
        var user = new ApplicationUser("user", "pass", "valid@gmail.com", Roles.Unknown);
        //mocking
        when(userStore.getUserById(any())).thenReturn(user);
        when(userStore.emailExists(any())).thenReturn(true);

        //assert
        assertThrows(EmailTakenException.class, () -> {
            //execute
            container.updateEmployee(editRequest); });

    }
    @Test
    public void cantUpdateEmployeeWithInvalidEmail() throws Exception {
        //setup
        var editRequest = new EditEmployeeRequest(new ObjectId().toString(), "", "", "", "invalid.com");
        var user = new ApplicationUser("user", "pass", "valid@gmail.com", Roles.Unknown);
        //mocking
        when(userStore.getUserById(any())).thenReturn(user);
        when(userStore.emailExists(any())).thenReturn(false);

        //assert
        assertThrows(InvalidEmailException.class, () -> {
            //execute
            container.updateEmployee(editRequest); });
    }
}