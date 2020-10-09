package com.joep.backofficeapi.DAL.Interfaces;

import com.joep.backofficeapi.Exceptions.CustomerNotFoundException;
import com.joep.backofficeapi.Exceptions.OrderNotFoundException;
import com.joep.backofficeapi.Models.Customer;
import com.joep.backofficeapi.Models.Order;
import com.joep.backofficeapi.Models.Orderstatus;
import org.bson.types.ObjectId;

import java.util.List;

public interface ICustomerStore {
    void addCustomer(Customer Customer);
    List<Customer> getCustomers();
    public Customer getCustomerByJwt(String jwt) throws CustomerNotFoundException;
    Customer getCustomerById(ObjectId id) throws CustomerNotFoundException;
}
