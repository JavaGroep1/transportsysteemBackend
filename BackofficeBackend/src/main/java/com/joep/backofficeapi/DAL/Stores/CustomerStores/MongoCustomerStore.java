package com.joep.backofficeapi.DAL.Stores.CustomerStores;

import com.joep.backofficeapi.DAL.Interfaces.ICustomerStore;
import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
import com.joep.backofficeapi.Models.Customer;
import org.bson.types.ObjectId;

import java.util.List;

public class MongoCustomerStore implements ICustomerStore {
    @Override
    public void addCustomer(Customer Customer) {

    }

    @Override
    public List<Customer> getCustomers() {
        return null;
    }

    @Override
    public List<Customer> getCustomersByCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer getCustomerById(ObjectId id) throws CustomerNotFoundException {
        return null;
    }
}
