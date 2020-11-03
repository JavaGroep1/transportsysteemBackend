package com.joep.backofficeapi.Util.Sanitizers;

import com.joep.backofficeapi.Models.Requests.Vehicle.EditVehicleRequest;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;

public class VehicleSanitizer {
    public static void sanitize(EditVehicleRequest vehicleRequest, Vehicle vehicle){
        if (vehicleRequest.getNewCapacityInKg() == 0) vehicleRequest.setNewCapacityInKg(vehicle.getCapacityInKG());
        if (vehicleRequest.getNewLicensePlate() == null) vehicleRequest.setNewLicensePlate(vehicle.getLicensePlate());
        if (vehicleRequest.getNewVehicleCategory() == null) vehicleRequest.setNewVehicleCategory(vehicle.getVehicleCategory());
    }
}
