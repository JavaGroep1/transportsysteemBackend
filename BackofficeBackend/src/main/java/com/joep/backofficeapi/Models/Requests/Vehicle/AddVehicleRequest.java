package com.joep.backofficeapi.Models.Requests.Vehicle;

import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;

public class AddVehicleRequest {
    public String licensePlate;
    public int capacityInKg;

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getCapacityInKg() {
        return capacityInKg;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public VehicleCategory vehicleCategory;
}
