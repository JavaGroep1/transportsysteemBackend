package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.ICustomerStore;
import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import org.bson.types.ObjectId;

import java.util.List;

public class CustomerContainer implements ICustomerStore {
    ICustomerStore store;

    public CustomerContainer(ICustomerStore store) {
        this.store = store;
    }

    @Override
    public void addCustomer(Customer Customer) {
        store.addCustomer(Customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return store.getCustomers();
    }

    @Override
    public Customer getCustomerByJwt(String jwt) throws Exception {
        return store.getCustomerByJwt(jwt);
    }

    @Override
    public Customer getCustomerById(ObjectId id) throws CustomerNotFoundException {
        return store.getCustomerById(id);
    }

    @Override
    public List<Customer> getActiveCustomers() {
        return store.getActiveCustomers();
    }

    @Override
    public void changeCustomerRole(Customer customer, Roles role) {
        store.changeCustomerRole(customer, role);
    }


}