package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.VehicleContainer;
import com.joep.backofficeapi.Exceptions.VehicleNotFoundException;
import com.joep.backofficeapi.Models.Requests.Vehicle.AddVehicleRequest;
import com.joep.backofficeapi.Models.Requests.Vehicle.EditVehicleRequest;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleContainer vehicleContainer;

    @PostMapping("/add")
    public ResponseEntity<?> addVehicle(@RequestBody AddVehicleRequest vehicle){
        vehicleContainer.addVehicle(new Vehicle(
                vehicle.getLicensePlate(),
                vehicle.getVehicleCategory(),
                vehicle.getCapacityInKg()
                ));
        return ResponseEntity.ok("ok");
    }

    @GetMapping("")
    public ResponseEntity<?> getVehicles(String licensePlate, VehicleCategory category) throws VehicleNotFoundException {
        if (category != null){
            return ResponseEntity.ok(vehicleContainer.getVehiclesByCategory(category));
        }
        if (licensePlate != null){
            return ResponseEntity.ok(vehicleContainer.getVehicleByPlate(licensePlate));
        }
        return ResponseEntity.ok(vehicleContainer.getVehicles());
    }

    @PutMapping("")
    public ResponseEntity<?> editVehicle(@RequestBody EditVehicleRequest request) throws VehicleNotFoundException {

        vehicleContainer.updateVehicle(request);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("")
    public ResponseEntity<?> removeVehicle(String vehicleid){
        vehicleContainer.deleteVehicle(new ObjectId(vehicleid));
        return ResponseEntity.ok("deleted");
    }

}
