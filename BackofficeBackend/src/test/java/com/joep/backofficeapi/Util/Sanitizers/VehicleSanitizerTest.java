package com.joep.backofficeapi.Util.Sanitizers;

import com.joep.backofficeapi.Models.Requests.Vehicle.EditVehicleRequest;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleSanitizerTest {

    @Test
    public void sanitizerFillsInMissingValues(){
        //setup
        var request = new EditVehicleRequest();
        var vehicle= new Vehicle("licensePlate", VehicleCategory.Car, 500);

        //execute
        VehicleSanitizer.sanitize(request,vehicle);

        //Assert
        assertEquals(vehicle.getCapacityInKG(), request.getNewCapacityInKg());
        assertEquals(vehicle.getLicensePlate(), request.getNewLicensePlate());
        assertEquals(vehicle.getVehicleCategory(), request.getNewVehicleCategory());

    }
    @Test
    public void sanitizerLeavesPopulatesValues(){
        //setup
        var request = new EditVehicleRequest("idstring", 100, "plate", VehicleCategory.Bike);
        var vehicle= new Vehicle("licensePlate", VehicleCategory.Car, 500);

        //execute
        VehicleSanitizer.sanitize(request,vehicle);

        //Assert
        assertNotEquals(vehicle.getCapacityInKG(), request.getNewCapacityInKg());
        assertNotEquals(vehicle.getLicensePlate(), request.getNewLicensePlate());
        assertNotEquals(vehicle.getVehicleCategory(), request.getNewVehicleCategory());

    }
}