package com.joep.backofficeapi.Models;

import dev.morphia.annotations.Entity;
import org.bson.types.ObjectId;

@Entity
public class Customer {
    @dev.morphia.annotations.Id
    private ObjectId Id;
    private Boolean isProspect;
    private String businessIdentifier;
    private String address;
    private String phoneNumber;


    public Customer(ObjectId id, Boolean isProspect, String businessIdentifier, String address, String phoneNumber) {
        Id = id;
        this.isProspect = isProspect;
        this.businessIdentifier = businessIdentifier;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
    }

    public ObjectId getId() {
        return Id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getBusinessIdentifier() {
        return businessIdentifier;
    }

    public Boolean getProspect() {
        return isProspect;
    }
}
