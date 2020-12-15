package com.joep.backofficeapi.DAL.Interfaces;

import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Requests.Customer.EditCustomerRequest;
import org.bson.types.ObjectId;

import java.util.List;

public interface ICustomerStore {
    void addCustomer(Customer Customer);
    List<Customer> getCustomers();
    Customer getCustomerByJwt(String jwt) throws Exception;
    Customer getCustomerById(ObjectId id) throws CustomerNotFoundException;
    List<Customer> getActiveCustomers();
    void deleteCustomer(String businessIdentifier);
    void updateCustomer(EditCustomerRequest editCustomerRequest) throws CustomerNotFoundException;
}
