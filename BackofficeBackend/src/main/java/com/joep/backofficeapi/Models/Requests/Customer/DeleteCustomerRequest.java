package com.joep.backofficeapi.Models.Requests.Customer;

public class DeleteCustomerRequest {
    private String businessIdentifier;

    public DeleteCustomerRequest(String businessIdentifier) {
        this.businessIdentifier = businessIdentifier;
    }

    public DeleteCustomerRequest() {
    }

    public String getBusinessIdentifier() {
        return businessIdentifier;
    }
}
