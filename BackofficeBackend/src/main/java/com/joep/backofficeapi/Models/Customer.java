package com.joep.backofficeapi.Models;

import dev.morphia.annotations.Entity;
import org.bson.types.ObjectId;

import java.util.Date;

@Entity
public class Customer {
    @dev.morphia.annotations.Id
    private ObjectId Id;
    private String name;
    private String businessIdentifier;
    private String address;
    private String phoneNumber;
    private Date dateStarted;


    public Customer(ObjectId id, String name, String businessIdentifier, String address, String phoneNumber, Date dateStarted) {
        Id = id;
        this.name = name;
        this.businessIdentifier = businessIdentifier;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateStarted = dateStarted;
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

    public Date getDateStarted() {
        return dateStarted;
    }
}
