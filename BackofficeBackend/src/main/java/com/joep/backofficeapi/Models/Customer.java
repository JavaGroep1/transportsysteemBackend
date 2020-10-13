package com.joep.backofficeapi.Models;

import dev.morphia.annotations.Entity;
import org.bson.types.ObjectId;

@Entity
public class Customer {
    @dev.morphia.annotations.Id
    private ObjectId Id;
    private String name;
    private String businessIdentifier;
    private String address;
    private String phoneNumber;


    public Customer(ObjectId id, String name, String businessIdentifier, String address, String phoneNumber) {
        Id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
