package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.IVehicleStore;
import com.joep.backofficeapi.Exceptions.VehicleNotFoundException;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import org.bson.types.ObjectId;

import java.util.List;


public class VehicleContainer implements IVehicleStore {

    private final IVehicleStore vehicleStore;
    public VehicleContainer(IVehicleStore vehicleStore) {
        this.vehicleStore = vehicleStore;
    }

    @Override
    public void addVehicle(Vehicle Vehicle) {
        vehicleStore.addVehicle(Vehicle);
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleStore.getVehicles();
    }

    @Override
    public Vehicle getVehicleById(ObjectId id) throws VehicleNotFoundException {
        return vehicleStore.getVehicleById(id);
    }

    @Override
    public void changeVehicleCategory(Vehicle Vehicle, VehicleCategory newVehicleCat) {
        vehicleStore.changeVehicleCategory(Vehicle, newVehicleCat);
    }
}
