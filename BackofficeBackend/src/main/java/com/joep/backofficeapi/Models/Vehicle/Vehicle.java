package com.joep.backofficeapi.Models.Vehicle;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity
public class Vehicle {

    @Id
    public ObjectId id;

    private String licensePlate;

    private int capacityInKG;
    
    private VehicleCategory vehicleCategory;

    private double kmPerLiter;

    public Vehicle(String licensePlate, VehicleCategory vehicleCategory, int capacityInKG, double kmPerLiter) {
        this.licensePlate = licensePlate;
        this.capacityInKG = capacityInKG;
        this.vehicleCategory = vehicleCategory;
        this.kmPerLiter = kmPerLiter;
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
        return id;
    }

    public String getIdString() {return  id.toString();}

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public double getKmPerLiter() {
        return kmPerLiter;
    }
}
