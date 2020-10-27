package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.VehicleContainer;
import com.joep.backofficeapi.Exceptions.VehicleNotFoundException;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
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
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle){
        vehicleContainer.addVehicle(vehicle);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("")
    public ResponseEntity<?> getVehicles(){
        return ResponseEntity.ok(vehicleContainer.getVehicles());
    }

    @GetMapping("")
    public ResponseEntity<?> getVehicles(String id) throws VehicleNotFoundException {
        return ResponseEntity.ok(vehicleContainer.getVehicleById(new ObjectId(id)));
    }
}
