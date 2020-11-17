package com.joep.backofficeapi.Models.Requests.Customer;

import org.bson.types.ObjectId;

public class EditCustomerRequest {
    private ObjectId customerIdString;
    private String businessIdentifier;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private Boolean prospect;

    public EditCustomerRequest(String businessIdentifier, String name, String address, String phoneNumber) {
        this.businessIdentifier = businessIdentifier;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public EditCustomerRequest() {
    }

    public Boolean getProspect() {
        return prospect;
    }

    public void setProspect(Boolean prospect) {
        this.prospect = prospect;
    }

    public ObjectId getCustomerIdString() {
        return customerIdString;
    }

    public void setCustomerIdString(ObjectId customerIdString) {
        this.customerIdString = customerIdString;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBusinessIdentifier() {
        return businessIdentifier;
    }

    public void setBusinessIdentifier(String businessIdentifier) {
        this.businessIdentifier = businessIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
