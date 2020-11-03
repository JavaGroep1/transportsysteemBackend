package com.joep.backofficeapi.Models.Vehicle;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.experimental.Name;
import org.bson.types.ObjectId;

@Entity
public class Vehicle {

    @Id
    public ObjectId id;

    private String licensePlate;

    private int capacityInKG;
    
    private VehicleCategory vehicleCategory;

    public Vehicle(String licensePlate, VehicleCategory vehicleCategory, int capacityInKG) {
        this.licensePlate = licensePlate;
        this.capacityInKG = capacityInKG;
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
        return id;
    }

    public String getIdString() {return  id.toString();}
    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }
}
