package com.joep.backofficeapi.Models.Requests.Customer;

import com.joep.backofficeapi.Models.Authentication.Roles;
import org.bson.types.ObjectId;

public class ChangeCustomerRequest {
    private ObjectId customerId;
    private Roles role;

    public ChangeCustomerRequest(ObjectId customerId, Roles role) {
        this.customerId = customerId;
        this.role = role;
    }

    public ObjectId getCustomerId() {
        return customerId;
    }

    public Roles getRole() {
        return role;
    }
}
