package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.ICustomerStore;
import com.joep.backofficeapi.DAL.Interfaces.ITicketStore;
import com.joep.backofficeapi.DAL.Interfaces.IUserStore;
import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.EditCustomerRequest;
import com.joep.backofficeapi.Models.Ticket.Ticket;
import com.joep.backofficeapi.Util.JwtUtil;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerContainerTest {

    private CustomerContainer container;
    private ICustomerStore customerStore;
    private UserStoreContainer userStoreContainer;

    @Before
    public void setup() {
        customerStore = mock(ICustomerStore.class);
        userStoreContainer = mock(UserStoreContainer.class);
        container = new CustomerContainer(customerStore, new JwtUtil(),userStoreContainer);
    }

    @Test
    public void canAddCustomer() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var sampleUser = new ApplicationUser("name", "pass", "email", Roles.Customer);
        var customer = new Customer();
        //execute
        container.addCustomer(customer);
    }

    @Test
    public void canGetAllCustomers() {

        //setup
        var customersToReturn = new ArrayList<Customer>();
        customersToReturn.add(new Customer());
        customersToReturn.add(new Customer());

        //Mocking
        when(customerStore.getCustomers()).thenReturn(customersToReturn);

        //execute
        var customers = container.getCustomers();

        //assert
        assertTrue(customers.size() > 0);
    }

    private String token;
    @Before
    public void Setup() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        var util = new JwtUtil();
        var userDetails = new ApplicationUser("username", "pass", "email@gmail.com", Roles.Customer);
        token=util.generateToken(userDetails);
    }

    @Test
    public void canGetByJWT() throws Exception {
        //setup
        var customerToReturn = new Customer();
        ApplicationUser user = new ApplicationUser();
        user.setCustomer(customerToReturn);


        //mocking
        when(userStoreContainer.getUserByName(any())).thenReturn(user);

        //execute
        var customer = container.getCustomerByJwt(token);

        assertEquals(customer, customerToReturn);
    }

    @Test
    public void canGetById() throws Exception {
        //setup
        ObjectId id = new ObjectId();
        var customerToReturn = new Customer();
        //mocking
        when(customerStore.getCustomerById(id)).thenReturn(customerToReturn);

        //execute
        var customer = container.getCustomerById(id);

        assertEquals(customer, customerToReturn);
    }

    @Test
    public void canGetActive(){
        //setup
        var customersToReturn = new ArrayList<Customer>();
        customersToReturn.add(new Customer());
        customersToReturn.add(new Customer());
        //mocking
        when(customerStore.getActiveCustomers()).thenReturn(customersToReturn);

        //execute
        var customers = container.getActiveCustomers();

        assertEquals(customers, customersToReturn);
    }

    @Test
    public void canDeleteCustomer(){
        //setup
        String identifier = "";

        //execute
        container.deleteCustomer(identifier);
    }

    @Test
    public void CanUpdateCustomer() throws CustomerNotFoundException {
        //setup
        var customerToReturn = new Customer();
        var editCustomerRequest = new EditCustomerRequest("identifier", "name", "address", "phonenumber");

        //mocking
        when(customerStore.getCustomerById(any())).thenReturn(customerToReturn);

        //execute
        container.updateCustomer(editCustomerRequest);
    }

    @Test
    public void CanUpdateCustomerWithEmptyValues() throws CustomerNotFoundException {
        //setup
        ObjectId id = new ObjectId();
        var customerToReturn = new Customer();
        var editCustomerRequest = new EditCustomerRequest("", "", "", "");

        //mocking
        when(customerStore.getCustomerById(any())).thenReturn(customerToReturn);

        //execute
        container.updateCustomer(editCustomerRequest);
    }

    @Test
    public void CanUpdateCustomerWithPartialEmptyValues() throws CustomerNotFoundException {
        //setup
        ObjectId id = new ObjectId();
        var customerToReturn = new Customer();
        var editCustomerRequest = new EditCustomerRequest("Identifier", null, "", "");

        //mocking
        when(customerStore.getCustomerById(any())).thenReturn(customerToReturn);

        //execute
        container.updateCustomer(editCustomerRequest);
    }

    @Test
    public void canInitiate(){
        new CustomerContainer(customerStore);
    }


}