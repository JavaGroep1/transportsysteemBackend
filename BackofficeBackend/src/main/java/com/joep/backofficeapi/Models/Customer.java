package com.joep.backofficeapi.Models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity
public class Customer {
    @dev.morphia.annotations.Id
    private ObjectId Id;

    public Customer(ObjectId id) {
        Id = id;
    }

    public Customer() {
    }

    public ObjectId getId() {
        return Id;
    }
}
