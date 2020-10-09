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

    private VehicleCategory vehicleCategory;

    public Vehicle(ObjectId id, String licensePlate, VehicleCategory vehicleCategory) {
        Id = id;
        this.licensePlate = licensePlate;
        this.vehicleCategory = vehicleCategory;
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

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }
}
