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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleContainer vehicleContainer;

    @PostMapping("")
    public ResponseEntity<?> addVehicle(@RequestBody AddVehicleRequest vehicle) {
        vehicleContainer.addVehicle(new Vehicle(
                vehicle.getLicensePlate(),
                vehicle.getVehicleCategory(),
                vehicle.getCapacityInKg(),
                vehicle.getKmPerLiter()
        ));
        return ResponseEntity.ok("ok");
    }

    @GetMapping("")
    public ResponseEntity<?> getVehicles() throws VehicleNotFoundException {
        return ResponseEntity.ok(vehicleContainer.getVehicles());
    }

    @GetMapping(path = "/{licensePlate}")
    public ResponseEntity<?> getVehicles(@PathVariable("licensePlate") String licensePlate) throws VehicleNotFoundException {
        return ResponseEntity.ok(vehicleContainer.getVehicleByPlate(licensePlate));
    }

    @GetMapping(value = "", params = "category")
    public ResponseEntity<?> getVehicles(VehicleCategory category) throws VehicleNotFoundException {
        return ResponseEntity.ok(vehicleContainer.getVehiclesByCategory(category));
    }

    @PutMapping("")
    public ResponseEntity<?> editVehicle(@RequestBody EditVehicleRequest request) throws VehicleNotFoundException {
        vehicleContainer.updateVehicle(request);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeVehicle(@PathVariable("id") String vehicleid) {
        vehicleContainer.deleteVehicle(new ObjectId(vehicleid));
        return ResponseEntity.ok("deleted");
    }

}
