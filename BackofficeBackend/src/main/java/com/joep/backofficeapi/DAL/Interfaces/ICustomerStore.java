package com.joep.backofficeapi.DAL.Interfaces;

import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import org.bson.types.ObjectId;

import java.util.List;

public interface ICustomerStore {
    void addCustomer(Customer Customer);
    List<Customer> getCustomers();
    public Customer getCustomerByJwt(String jwt) throws Exception;
    Customer getCustomerById(ObjectId id) throws CustomerNotFoundException;
    List<Customer> getActiveCustomers();
    void changeCustomerRole(Customer customer, Roles role);
}
