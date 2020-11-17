package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.ICustomerStore;
import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.EditCustomerRequest;
import com.joep.backofficeapi.Util.Sanitizers.CustomerSanitizer;
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

    public void deleteCustomer(String businessIdentifier) {
        store.deleteCustomer(businessIdentifier);
    }



    @Override
    public void updateCustomer(EditCustomerRequest editCustomerRequest) throws CustomerNotFoundException {
        //still returns 0 from frontend
        Customer customer = store.getCustomerById(editCustomerRequest.getCustomerIdString());
        CustomerSanitizer.sanitize(editCustomerRequest, customer);
        store.updateCustomer(editCustomerRequest);

        //change role if prospect changed

        //maybe change email / account details later too?
    }
}