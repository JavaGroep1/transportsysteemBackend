package com.joep.backofficeapi.Models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity
public class Vehicle {

    @Id
    private ObjectId Id;

    private String licensePlate;

    private int capacityInKG;

    public Vehicle(ObjectId id, String licensePlate) {
        Id = id;
        this.licensePlate = licensePlate;
    }

    public Vehicle() {
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getCapacityInKG() {
        return capacityInKG;
    }

    public ObjectId getId() {
        return Id;
    }
}
