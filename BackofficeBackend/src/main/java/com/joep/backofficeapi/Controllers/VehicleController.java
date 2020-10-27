package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.VehicleContainer;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"})
public class VehicleController {

    @Autowired
    private VehicleContainer vehicleContainer;

    @PostMapping("/vehicles/add")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle){
        vehicleContainer.addVehicle(vehicle);
        return ResponseEntity.ok("ok");
    }
}
