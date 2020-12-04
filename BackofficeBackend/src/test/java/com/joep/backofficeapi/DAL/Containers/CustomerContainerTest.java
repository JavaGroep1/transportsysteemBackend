package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.ICustomerStore;
import com.joep.backofficeapi.DAL.Interfaces.ITicketStore;
import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.EditCustomerRequest;
import com.joep.backofficeapi.Models.Ticket.Ticket;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerContainerTest {

    private CustomerContainer container;
    private ICustomerStore customerStore;

    @Before
    public void setup() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        customerStore = mock(ICustomerStore.class);
        container = new CustomerContainer(customerStore);
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
    public void canGetAllCustomers() throws UnsupportedEncodingException, NoSuchAlgorithmException {

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


    @Test
    public void canGetByJWT() throws Exception {
        //setup
        String jwt = "";
        var customerToReturn = new Customer();
        //mocking
        when(customerStore.getCustomerByJwt(any())).thenReturn(customerToReturn);

        //execute
        var customer = container.getCustomerByJwt(jwt);

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


}