package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.ICustomerStore;
import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
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

    }

    @Override
    public List<Customer> getCustomers() {
        return null;
    }

    @Override
    public Customer getCustomerByJwt(String jwt) {
        return null;
    }

    @Override
    public Customer getCustomerById(ObjectId id) throws CustomerNotFoundException {
        return store.getCustomerById(id);
    }
}
