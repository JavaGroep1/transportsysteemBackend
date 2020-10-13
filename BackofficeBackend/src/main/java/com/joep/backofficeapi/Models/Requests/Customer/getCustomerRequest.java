package com.joep.backofficeapi.Models.Requests.Customer;

import org.bson.types.ObjectId;

public class getCustomerRequest {
    private ObjectId customerId;

    public getCustomerRequest(ObjectId customerId) {
        this.customerId = customerId;
    }

    public ObjectId getCustomerId() {
        return customerId;
    }
}
