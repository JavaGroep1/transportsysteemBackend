package com.joep.backofficeapi.DAL.Interfaces;

import com.joep.backofficeapi.Exceptions.VehicleNotFoundException;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import org.bson.types.ObjectId;

import java.util.List;

public interface IVehicleStore {
    void addVehicle(Vehicle Vehicle);
    List<Vehicle> getVehicles();
    Vehicle getVehicleById(ObjectId id) throws VehicleNotFoundException;
    void changeVehicleCategory(Vehicle Vehicle, VehicleCategory newVehicleCat);
}
