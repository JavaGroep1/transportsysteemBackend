package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.DAL.Containers.VehicleContainer;
import com.joep.backofficeapi.Exceptions.VehicleNotFoundException;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Requests.Vehicle.AddVehicleRequest;
import com.joep.backofficeapi.Models.Requests.Vehicle.EditVehicleRequest;
import com.joep.backofficeapi.Models.Vehicle.Vehicle;
import com.joep.backofficeapi.Models.Vehicle.VehicleCategory;
import com.joep.backofficeapi.Util.Authorization.RoleAuthorization;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleContainer vehicleContainer;
    private final RoleAuthorization roleAuthorization;

    public VehicleController(VehicleContainer vehicleContainer,  UserStoreContainer userStoreContainer) {
        this.vehicleContainer = vehicleContainer;
        this.roleAuthorization = new RoleAuthorization(userStoreContainer);
    }

    @PostMapping("")
    public ResponseEntity<?> addVehicle(HttpServletRequest request, @RequestBody AddVehicleRequest vehicle) throws Exception {
        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

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
    public ResponseEntity<?> getVehicles(HttpServletRequest request, @PathVariable("licensePlate") String licensePlate) throws Exception {
        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

        return ResponseEntity.ok(vehicleContainer.getVehicleByPlate(licensePlate));
    }

    @GetMapping(value = "", params = "category")
    public ResponseEntity<?> getVehicles(VehicleCategory category) throws VehicleNotFoundException {
        return ResponseEntity.ok(vehicleContainer.getVehiclesByCategory(category));
    }

    @PutMapping("")
    public ResponseEntity<?> editVehicle(HttpServletRequest HttpRequest, @RequestBody EditVehicleRequest request) throws Exception {
        roleAuthorization.checkRole(HttpRequest, new Roles[]{Roles.Admin, Roles.Employee});

        vehicleContainer.updateVehicle(request);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeVehicle(HttpServletRequest request, @PathVariable("id") String vehicleid) throws Exception {
        roleAuthorization.checkRole(request, new Roles[]{Roles.Admin, Roles.Employee});

        vehicleContainer.deleteVehicle(new ObjectId(vehicleid));
        return ResponseEntity.ok("deleted");
    }

    @GetMapping(path = "/weight/{weight}")
    public ResponseEntity<?> getVehiclesByWeight(@PathVariable String weight) throws VehicleNotFoundException {
        return ResponseEntity.ok(vehicleContainer.getVehiclesByWeight(Integer.parseInt(weight)));
    }

}
