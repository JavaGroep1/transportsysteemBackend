package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.CustomerContainer;
import com.joep.backofficeapi.DAL.Containers.OrderContainer;
import com.joep.backofficeapi.DAL.Containers.VehicleContainer;
import com.joep.backofficeapi.DAL.Stores.CustomerStores.MongoCustomerStore;
import com.joep.backofficeapi.DAL.Stores.OrderStores.MongoOrderStore;
import com.joep.backofficeapi.DAL.Stores.VehicleStores.MongoVehicleStore;
import com.joep.backofficeapi.Models.Order;
import com.joep.backofficeapi.Models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
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
