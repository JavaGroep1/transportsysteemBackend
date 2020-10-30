package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.VehicleContainer;
import com.joep.backofficeapi.Exceptions.VehicleNotFoundException;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
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

    @PostMapping(
            value = "/add",
            headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle) throws URISyntaxException {
        vehicleContainer.addVehicle(vehicle);
        return ResponseEntity.ok("created");
    }

    @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Vehicle>> getVehicles(){
        return ResponseEntity.ok(vehicleContainer.getVehicles());
    }

    @GetMapping(value = "", params = "id")
    @ResponseBody
    public ResponseEntity<Vehicle> getVehicles(@RequestParam String id) throws VehicleNotFoundException {
        return ResponseEntity.ok(vehicleContainer.getVehicleById(new ObjectId(id)));
    }
}
